package ru.geometrica.GeometricaBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.security.SecureRandom;

@Entity
@Table(name = "authorization_tokens")
public class Token {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;

	@ManyToOne
	@JoinColumn
	public User user;

	@NotNull
	public byte[] authorizationToken;
	
	public Token() {}

	public Token(User user) {
		this.user = user;

		SecureRandom sr = new SecureRandom();

		this.authorizationToken	= new byte[16];
		sr.nextBytes(authorizationToken);
	}
}
