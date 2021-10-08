package com.tekup.restapi.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.MenuItem;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
	Optional<MenuItem> findById(Long id);

	Optional<MenuItem> findByName(String name);

	Optional<MenuItem> findByDescription(String description);
	
	@Query(value = "SELECT m from MenuItem m where m.restaurant.id = :restaurantId")
	Optional<List<MenuItem>> findMenusByRestaurantId(@Param("restaurantId") int restaurantId);
}
