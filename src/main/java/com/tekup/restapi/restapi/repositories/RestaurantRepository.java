package com.tekup.restapi.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.tekup.restapi.restapi.models.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {    
    Restaurant findByName(String name);
}
