package ru.geometrica.GeometricaBackend.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "media", path = "media")
public interface MediaRepository extends CrudRepository<Media, String> {
	Media findByName(String name);

	List<Media> findByType(String type);

	List<Media> findByIllustratedTile(Tile illustrates);
}
