package ru.geometrica.GeometricaBackend.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(long id);

	User findByEmailAdress(String emailAdress);

	User findByPhoneNumber(String phoneNumber);
}
