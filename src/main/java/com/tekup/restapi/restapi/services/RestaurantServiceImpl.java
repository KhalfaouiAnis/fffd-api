package com.tekup.restapi.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.restapi.models.Restaurant;
import com.tekup.restapi.restapi.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService{
	
	@Autowired
	RestaurantRepository restauRepository;

	@Override
	public Restaurant findByName(String name) {
		return null;
	}

	@Override
	public void save(Restaurant restaurant) {
		Restaurant restau = new Restaurant(restaurant.getName(), restaurant.getDescription(), restaurant.getAddress());
		System.out.println(restau);
		restauRepository.save(restau);
	}
}
