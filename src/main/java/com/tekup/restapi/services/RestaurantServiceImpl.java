package com.tekup.restapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.models.MenuItem;
import com.tekup.restapi.models.Restaurant;
import com.tekup.restapi.models.User;
import com.tekup.restapi.repositories.MenuItemRepository;
import com.tekup.restapi.repositories.RestaurantRepository;
import com.tekup.restapi.repositories.UserRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

	@Autowired
	RestaurantRepository restauRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MenuItemRepository menuRepository;

	@Override
	public Restaurant findByName(String name) {
		return restauRepository.findByName(name).get();
	}

	@Override
	public Restaurant save(Restaurant restaurant) throws Exception {
		Restaurant restau = new Restaurant(restaurant.getName(), restaurant.getDescription(), restaurant.getAddress());
		try {
			// add restaurant managers
			if (restaurant.getManagers() != null) {
				List<User> managers = new ArrayList<User>();
				restaurant.getManagers().forEach(manager -> {
					User mgr = userRepository.findById(manager.getId()).get();
					if (mgr != null) {
						managers.add(mgr);
					}
				});
				restau.setManagers(managers);
			}

			// add restaurant Menu items
			if (restaurant.getMenu() != null) {
				List<MenuItem> menu = new ArrayList<MenuItem>();
				restaurant.getMenu().forEach(menuItem -> {
					MenuItem menuitem = menuRepository.findById(menuItem.getId()).get();
					if (menuitem != null) {
						menu.add(menuitem);
					}
				});
				restau.setMenu(menu);
			}
		} catch (Exception e) {
			throw new Exception("Error accured while creating the restaurant");
		}
		System.out.println(restau);
		restauRepository.save(restau);
		return restau;
	}

	@Override
	public void delete(Restaurant restaurant) {
		restauRepository.delete(restaurant);
	}

	@Override
	public Restaurant findById(int restaurantId) {
		return restauRepository.findById(restaurantId).get();
	}

	@Override
	public List<Restaurant> findAll() {
		return (List<Restaurant>) restauRepository.findAll();
	}
}
