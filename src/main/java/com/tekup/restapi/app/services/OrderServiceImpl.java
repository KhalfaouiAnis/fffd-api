package com.tekup.restapi.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.app.models.MenuItem;
import com.tekup.restapi.app.models.Order;
import com.tekup.restapi.app.models.Restaurant;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.repositories.MenuItemRepository;
import com.tekup.restapi.app.repositories.OrderRepository;
import com.tekup.restapi.app.repositories.RestaurantRepository;
import com.tekup.restapi.app.repositories.UserRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	MenuItemRepository menuRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	RestaurantRepository restoRepo;

	@Override
	public Order findById(int id) {
		return orderRepo.findById(id);
	}

	@Override
	public List<Order> findByCustomerId(int id) {
		return orderRepo.findByUserId(id);
	}
	
	@Override
	public List<Order> getByRestaurantId(int id) {
		return orderRepo.findByRestaurantId(id).get();
	}

	@Override
	public void placeOrder(Order order, int restaurantid) {
		User user = userRepo.findById(order.getUser().getId()).get();
		Restaurant resto = restoRepo.findById(restaurantid).get();

		order.setUser(user);
		order.setRestaurant(resto);
		order.setOrderDate(new Date());
		List<MenuItem> items = new ArrayList<MenuItem>();
		order.getItems().forEach(item -> {
			if (menuRepo.findById(item.getId()).isPresent()) {
				items.add(menuRepo.findById(item.getId()).get());
			}
		});
		order.setItems(items);
		orderRepo.save(order);
	}

	@Override
	public List<Order> getTodayOrdersByRestoId(int restoId) {
		return orderRepo.findByRestaurantIdAndDateEqualToToday(restoId).get();
	}

	@Override
	public boolean deliverOrder(int orderId) {
		Order savedOrder = orderRepo.findById(orderId);
		if (savedOrder != null) {
			savedOrder.setOrderStatus("Delivered");
			orderRepo.save(savedOrder);
			return true;
		}
		return false;

	}

}
