package ru.geometrica.GeometricaBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carts")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	@NotNull
	@ManyToOne
	@JoinColumn
	public User customer;
	@NotNull
	@ManyToOne
	@JoinColumn
	public Tile item;
}
