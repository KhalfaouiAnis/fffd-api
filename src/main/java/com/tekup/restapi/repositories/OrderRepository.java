package com.tekup.restapi.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	Optional<Order> findByDeliveryStatus(boolean status);

	Optional<Order> findByOrderDate(Date orderDate);

	@Query(value = "SELECT o from Order o where o.user.id = :userId")
	Optional<Order> findByUserId(@Param("userId") int userId);
}
