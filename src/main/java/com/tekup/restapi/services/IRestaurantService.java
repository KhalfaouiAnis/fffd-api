package com.tekup.restapi.services;

import java.util.List;

import com.tekup.restapi.models.Restaurant;

public interface IRestaurantService {
	public Restaurant findByName(String name);
	public Restaurant findById(int restaurantId);
	public List<Restaurant> findAll();

	public Restaurant save(Restaurant restaurant) throws Exception;

	public void delete(Restaurant restaurant);
}
