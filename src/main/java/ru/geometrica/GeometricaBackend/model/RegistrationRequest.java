package ru.geometrica.GeometricaBackend.model;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationRequest {
	public User userData;
	public String password;
}
