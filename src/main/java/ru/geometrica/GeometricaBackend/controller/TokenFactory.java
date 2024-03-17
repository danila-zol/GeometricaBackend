package ru.geometrica.GeometricaBackend.controller;

import ru.geometrica.GeometricaBackend.model.Token;
import ru.geometrica.GeometricaBackend.model.User;

public class TokenFactory {
	public Token getToken(User user, String password) {

		return new Token(user);
	}
}
