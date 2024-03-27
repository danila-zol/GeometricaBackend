package ru.geometrica.GeometricaBackend.security;

import org.aspectj.bridge.Message;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256PasswordEncoder implements PasswordEncoder {
	private final MessageDigest md = MessageDigest.getInstance("SHA-256");

	public SHA256PasswordEncoder() throws NoSuchAlgorithmException {}

	@Override
	public String encode(CharSequence rawPassword) {
		return new String(this.md.digest(
				rawPassword
						.toString()
						.getBytes(StandardCharsets.UTF_8)));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return MessageDigest.isEqual(
				rawPassword.toString().getBytes(StandardCharsets.UTF_8),
				encodedPassword.getBytes(StandardCharsets.UTF_8));
	}
}
