package com.tekup.restapi.app.services;

import java.util.List;

import com.tekup.restapi.app.models.Order;

public interface IOrderService {
	
	public Order findById(int id);

	public List<Order> findByCustomerId(int id);
	
	public List<Order> getByRestaurantId(int id);

	public List<Order> getTodayOrdersByRestoId(int restoId);

	public void placeOrder(Order order, int restaurantid) throws NoSuchFieldException;
	
	public boolean deliverOrder(int orderId);
}
