package ru.geometrica.GeometricaBackend.controller;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.geometrica.GeometricaBackend.model.*;

@RestController
public class GeometricaController {

	private final UserRepository userRepository;
	private final TilesRepository tilesRepository;
	private final MediaRepository mediaRepository;
	private final TokenRepository tokenRepository;
	private final TokenFactory tokenFactory = new TokenFactory();
	private final PasswordEncoder passwordEncoder;

	GeometricaController(
			UserRepository userRepository,
			TilesRepository tilesRepository,
			MediaRepository mediaRepository,
			TokenRepository tokenRepository,
			PasswordEncoder passwordEncoder
	) {
		this.userRepository  = userRepository;
		this.tilesRepository = tilesRepository;
		this.mediaRepository = mediaRepository;
		this.tokenRepository = tokenRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/tokens/recieve")
	public Token exchangeAuthToken(@RequestBody TokenRequest tokenRequest) {
		User targetUser;
		if (!tokenRequest.email.isEmpty()) {
			targetUser = userRepository.findByEmailAdress(tokenRequest.email);
		} else if (!tokenRequest.phone.isEmpty()) {
			targetUser = userRepository.findByPhoneNumber(tokenRequest.phone);
		} else {
			return new Token();
		}

		Token newToken = new Token(targetUser);

		tokenRepository.save(newToken);
		return newToken;
	}

	@PostMapping("/customers/register")
	public void registerUser(@RequestBody RegistrationRequest request)  {
		request.userData.setPasswordHash(request.password, passwordEncoder);
		userRepository.save(request.userData);
	}

	@PostMapping("/media/upload")
	public void uploadImage(@RequestBody MediaUpload newMedia) throws IOException {
		// Resolve tile IDs to tile objects
		Vector<Tile> illustratedTiles = new Vector<Tile>();
		for (long illustratedTileId : newMedia.illustrates) {
			illustratedTiles.add(tilesRepository.findById(illustratedTileId));
		}

		// Generate and save new media objects
		for (Tile illustratedTile : illustratedTiles) {
			mediaRepository.save(new Media((FileUpload)newMedia, illustratedTile));
		}
	}

	@GetMapping("/test")
	public String test() {
		return "The server is running";
	}
}
