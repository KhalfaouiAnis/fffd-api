package com.tekup.restapi.restapi.services;

import java.util.Date;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Restaurant restaurant) {
		Restaurant restau = new Restaurant(restaurant.getName(), restaurant.getDescription(), restaurant.getAddress());
		restau.setCreationDate(new Date());
		System.out.println(restau);
		restauRepository.save(restau);
	}
}
