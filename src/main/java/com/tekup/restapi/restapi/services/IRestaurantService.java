package com.tekup.restapi.restapi.services;

import com.tekup.restapi.restapi.models.Restaurant;

public interface IRestaurantService {
	
	public Restaurant findByName(String name);	
	public void save(Restaurant restaurant);
}
