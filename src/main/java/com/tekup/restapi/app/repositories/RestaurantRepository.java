package com.tekup.restapi.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
	Optional<Restaurant> findByName(String name);

	Optional<Restaurant> findById(int id);

	@Query(value = "SELECT * FROM restaurants r INNER JOIN reviews re ON r.id = re.restaurant_id GROUP BY r.id ORDER BY re.rating DESC LIMIT 3", nativeQuery = true)
	Optional<List<Restaurant>> getFeaturedRestaurants();

	@Query(value = "SELECT r FROM Restaurant r where r.id in (select u.restaurant.id from User u where u.id = :managerId)")
	Optional<Restaurant> findByManagerId(@Param("managerId") int managerId);

}
