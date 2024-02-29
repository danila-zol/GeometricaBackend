package ru.geometrica.GeometricaBackend.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TilesRepository extends CrudRepository<Tile, Long> {

	List<Tile> findByType(String type);

	Tile findById(long id);
}
