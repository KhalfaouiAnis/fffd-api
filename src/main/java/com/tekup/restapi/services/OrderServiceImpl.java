package com.tekup.restapi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.models.MenuItem;
import com.tekup.restapi.models.Order;
import com.tekup.restapi.models.User;
import com.tekup.restapi.repositories.MenuItemRepository;
import com.tekup.restapi.repositories.OrderRepository;
import com.tekup.restapi.repositories.UserRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	MenuItemRepository menuRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public Order findById(int id) {
		return orderRepo.findById(id).get();
	}

	@Override
	public Order findByUserId(int userId) {
		return orderRepo.findByUserId(userId).get();
	}

	@Override
	public void setDeliveryStatus(int orderId, boolean newStatus) {
		Order order = orderRepo.findById(orderId).get();
		order.setDeliveryStatus(newStatus);
		orderRepo.save(order);
	}

	@Override
	public void placeOrder(Order order) throws NoSuchFieldException {
		order.setOrderDate(new Date());
		System.out.println("coming order: " + order);
		User user = userRepo.findById(order.getUser().getId()).get();
		order.setUser(user);
		System.out.println("coming user: " + user);
		if (user == null)
			throw new NoSuchFieldException();

		System.out.println("coming items: " + order.getItems());
		List<MenuItem> items = new ArrayList<MenuItem>();
		order.getItems().forEach(item -> {
			MenuItem itm = menuRepo.findById(item.getId()).get();
			if (itm != null) {
				items.add(itm);
			}
		});
		order.setItems(items);
		System.out.println("Final order: " + order);
		orderRepo.save(order);
		Order r = orderRepo.findByUserId(order.getUser().getId()).get();
		System.out.println("order: " + r);
	}

}
