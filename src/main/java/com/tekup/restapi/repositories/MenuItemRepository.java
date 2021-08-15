package com.tekup.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.models.MenuItem;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem,Long>{
	Optional<MenuItem> findById(Long id);
	Optional<MenuItem> findByName(String name);
	Optional<MenuItem> findByCategory(String category);
	Optional<MenuItem> findByDescription(String description);
}
