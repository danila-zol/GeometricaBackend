package ru.geometrica.GeometricaBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tiles")
public class Tile {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;
	public String type;
	public String name;
	public String description;

	protected Tile() {}

	public Tile(String type, String name, String description) {
		this.type = type;
		this.name = name;
		this.description = description;
	}
}
