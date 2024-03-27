package ru.geometrica.GeometricaBackend.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List<CartItem> findByCustomer(User user);
}
