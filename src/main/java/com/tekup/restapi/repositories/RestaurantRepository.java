package com.tekup.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.models.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {    
    Optional<Restaurant> findByName(String name);
}
