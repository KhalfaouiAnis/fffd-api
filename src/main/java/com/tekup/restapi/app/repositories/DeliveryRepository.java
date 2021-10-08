package com.tekup.restapi.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.Delivery;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {

}
