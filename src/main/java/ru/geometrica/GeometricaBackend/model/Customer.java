package ru.geometrica.GeometricaBackend.model;

import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private byte[] passwordHash = new byte[] {0x4E, 0x4F, 0x4F, 0x42};

	protected Customer() throws Exception {}

	public Customer(String firstName, String lastName) throws Exception {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(String firstName, String lastName, String password) throws Exception {

		this.firstName = firstName;
		this.lastName = lastName;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		this.passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash (String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		this.passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
	}
}