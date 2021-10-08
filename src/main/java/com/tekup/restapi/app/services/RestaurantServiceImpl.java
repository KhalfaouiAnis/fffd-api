package com.tekup.restapi.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.app.models.MenuItem;
import com.tekup.restapi.app.models.Restaurant;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.repositories.MenuItemRepository;
import com.tekup.restapi.app.repositories.OrderRepository;
import com.tekup.restapi.app.repositories.RestaurantRepository;
import com.tekup.restapi.app.repositories.UserRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

	@Autowired
	RestaurantRepository restauRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	MenuItemRepository menuRepo;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public Restaurant getByName(String name) {
		try {
			return restauRepo.findByName(name).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Override
	public Restaurant getById(int restaurantId) {
		try {
			return restauRepo.findById(restaurantId).get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Override
	public List<Restaurant> findAll() {
		return (List<Restaurant>) restauRepo.findAll();
	}

	@Override
	public List<Restaurant> getFeaturedRestaurants() {
		return (List<Restaurant>) restauRepo.getFeaturedRestaurants().get();
	}

	@Override
	public Restaurant getByManagerId(int managerId) {
		return restauRepo.findByManagerId(managerId).get();
	}

	@Override
	public Restaurant save(Restaurant restaurant) throws Exception {
		Restaurant restau = restaurant;
		List<User> managers = new ArrayList<User>();
		try {
			if (!restaurant.getManagers().isEmpty()) {
				restaurant.getManagers().forEach(manager -> {
					User mngr = userRepo.findById(manager.getId()).get();
					if (mngr != null) {
						mngr.setRestaurant(restau);
						managers.add(mngr);
					}
				});
				restau.setManagers(managers);
			}

			if (restaurant.getMenu() != null) {
				List<MenuItem> menu = new ArrayList<MenuItem>();
				restaurant.getMenu().forEach(menuItem -> {
					MenuItem menuitem = menuRepo.findById(menuItem.getId()).get();
					if (menuitem != null) {
						menu.add(menuitem);
					}
				});
				restau.setMenu(menu);
			}
			restauRepo.save(restau);
			userRepo.saveAll(managers);
			return restau;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void delete(Restaurant restaurant) {
		restauRepo.delete(restaurant);
	}

	@Override
	public void update(int id, Restaurant restaurant) {
		Restaurant fromDB = restauRepo.findById(id).get();
		if (restaurant.getName() != null) {
			fromDB.setName(restaurant.getName());
		}
		if (restaurant.getDescription() != null) {
			fromDB.setDescription(restaurant.getDescription());
		}

		if (restaurant.getCategory() != null) {
			fromDB.setCategory(restaurant.getCategory());
		}

		if (restaurant.getImage() != null) {
			fromDB.setImage(restaurant.getImage());
		}

		if (restaurant.getAddress() != null) {
			fromDB.setAddress(restaurant.getAddress());
		}

		if (restaurant.getWorkingHours() != null) {
			fromDB.setWorkingHours(restaurant.getWorkingHours());
		}
		restauRepo.save(fromDB);
	}

}
