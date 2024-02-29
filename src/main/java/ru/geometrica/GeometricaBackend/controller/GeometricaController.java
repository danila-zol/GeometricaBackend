package ru.geometrica.GeometricaBackend.controller;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geometrica.GeometricaBackend.model.*;

@RestController
public class GeometricaController {

	private final CustomerRepository customerRepository;
	private final TilesRepository tilesRepository;
	private final MediaRepository mediaRepository;

	GeometricaController(
			CustomerRepository customerRepository,
			TilesRepository tilesRepository,
			MediaRepository mediaRepository) {
		this.customerRepository = customerRepository;
		this.tilesRepository = tilesRepository;
		this.mediaRepository = mediaRepository;
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/customers/register")
	public void registerCustomer(@RequestBody Customer newCustomer) throws Exception {
//		if (newCustomer.hasPassword()) {
//			newCustomer.setPasswordHash(newCustomer.getPassword());
//		}

		customerRepository.save(newCustomer);
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
