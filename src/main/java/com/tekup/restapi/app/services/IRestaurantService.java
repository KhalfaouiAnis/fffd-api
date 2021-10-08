package com.tekup.restapi.app.services;

import java.util.List;

import com.tekup.restapi.app.models.Restaurant;

public interface IRestaurantService {
	public Restaurant getByName(String name);
	public Restaurant getById(int restaurantId);
	public Restaurant getByManagerId(int managerId);
	public List<Restaurant> findAll();

	public Restaurant save(Restaurant restaurant) throws Exception;

	public void update(int id, Restaurant restaurant);
	
	public void delete(Restaurant restaurant);
	
	public List<Restaurant> getFeaturedRestaurants();
}
