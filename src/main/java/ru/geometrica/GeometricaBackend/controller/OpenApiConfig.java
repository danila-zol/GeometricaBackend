package ru.geometrica.GeometricaBackend.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Geometrica API",
				description = "Geometrica.ru бэкенд. Я ищю работу.", version = "1.0.0",
				contact = @Contact(
						name = "Данила Золотарев",
						email = "danila-zol@list.ru",
						url = "https://github.com/danila-zol"
				)
		),
		servers = { @Server(url = "/"), @Server(url = "https://7db0-176-59-77-216.ngrok-free.app/")}
)
public class OpenApiConfig {
    
}
