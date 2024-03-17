package ru.geometrica.GeometricaBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "customers")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;

	@NotNull
	public String firstName;

	@NotNull
	public String lastName;

	public String patronymicName;

	@NotNull
	public String phoneNumber;

	@Email
	public String emailAdress;

	public String homeAdress;

	@NotNull
	Permissions permissionLevel;

	@NotNull
	public byte[] passwordHash;

	public static enum Permissions {USER, MANAGER, ADMIN}

	public User() {}

	public User(String firstName, String lastName) throws Exception {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String firstName, String lastName, String password) throws Exception {

		this.firstName = firstName;
		this.lastName = lastName;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		this.passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

	}

	public void setPasswordHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		this.passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
	}
}
