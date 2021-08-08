package com.tekup.restapi.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tekup.restapi.restapi.models.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem,Long>{
	Optional<MenuItem> findMenuItemById(Long id);
	Optional<MenuItem> findMenuItemByName(String name);
	Optional<MenuItem> findMenuItemByCategory(String category);
	Optional<MenuItem> findMenuItemByDescription(String description);
}
