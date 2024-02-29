package ru.geometrica.GeometricaBackend.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import jakarta.persistence.*;
import ru.geometrica.GeometricaBackend.GeometricaBackendApplication;

@Entity
@Table(name = "media")
public class Media {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public long id;
	public String name;
	public String type;
	public String path;

	@ManyToOne
	@JoinColumn(name = "illustrated_tile")
	public Tile illustratedTile;

	protected Media() {}

	public Media(String type, String name, String path, Tile illustrates) {
		this.type = type;
		this.name = name;
		this.path = path;
		this.illustratedTile = illustrates;
	}

	public Media(FileUpload fu, Tile illustratedTile) throws IOException {
		this.type = fu.type;
		this.name = fu.name;
		this.illustratedTile = illustratedTile;
		
	
		byte[] fileToWrite = Base64.getDecoder().decode(fu.file);
		Path fileUploadPath = GeometricaBackendApplication.mediaRepoRoot.resolve(this.name);
		Files.write(fileUploadPath, fileToWrite);
		this.path = fileUploadPath.toString();
	}
}
