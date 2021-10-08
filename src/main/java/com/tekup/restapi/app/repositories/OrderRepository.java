package com.tekup.restapi.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	@Query(value = "SELECT o from Order o where o.user.id in :userId")
	List<Order> findByUserId(@Param("userId") int userId);

	@Query(value = "SELECT o from Order o where o.restaurant.id IN :restoId")
	Optional<List<Order>> findByRestaurantId(@Param("restoId") int restoId);

	@Query(value = "SELECT o from Order o where o.restaurant.id IN :restoId AND date_format(o.orderDate,'%Y-%m-%d')= date_format(now(), '%Y-%m-%d')")
	Optional<List<Order>> findByRestaurantIdAndDateEqualToToday(@Param("restoId") int restoId);

	Order findById(int id);
}
