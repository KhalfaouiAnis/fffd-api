package com.tekup.restapi.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

	@Query(value = "SELECT r from Review r where r.user.id = :userId AND r.restaurant.id = :restaurantId")
	Optional<Review> findByUserIdAndRestaurantId(@Param("userId") int userId, @Param("restaurantId") int restaurantId);

	Review findById(int id);

	Optional<List<Review>> findByrestaurantId(@Param("restaurantId") int restaurantId);

	Optional<List<Review>> findByuserId(@Param("userId") int userId);

}
