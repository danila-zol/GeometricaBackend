package ru.geometrica.GeometricaBackend;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.geometrica.GeometricaBackend.model.*;
import ru.geometrica.GeometricaBackend.controller.*;

@SpringBootApplication
public class GeometricaBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(GeometricaBackendApplication.class);
	public static final Path applicationRoot = Paths.get("./");
	public static final Path mediaRepoRoot = Paths.get("./media_repository");


	public static void main(String[] args) throws IOException {
		if (!Files.exists(mediaRepoRoot)) {
			Files.createDirectory(mediaRepoRoot);
			System.out.println("Media repo created successfully");
		} else {
			System.out.println("Using existing media repo");
		}

		SpringApplication.run(GeometricaBackendApplication.class);
	}
}
