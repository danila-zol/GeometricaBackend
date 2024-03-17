package ru.geometrica.GeometricaBackend.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Geometrica API",
				description = "Geometrica.ru бэкенд. Я ищю работу.", version = "1.0.0",
				contact = @Contact(
						name = "Данила Золотарев",
						email = "danila-zol@list.ru",
						url = "https://github.com/danila-zol"
				)
		)
)
public class OpenApiConfig {
    
}
