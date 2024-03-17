package ru.geometrica.GeometricaBackend.model;

import java.security.NoSuchAlgorithmException;

class RegistrationRequest {
	User user;
	String password;

	public User GetUser() throws NoSuchAlgorithmException {
		user.setPasswordHash(this.password);
		return user;
	}
}
