package com.tekup.restapi.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.models.MenuItem;
import com.tekup.restapi.models.Restaurant;
import com.tekup.restapi.repositories.MenuItemRepository;
import com.tekup.restapi.repositories.RestaurantRepository;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

	@Autowired
	MenuItemRepository menuRepository;
	
	@Autowired
	RestaurantRepository restauRepository;

	@Override
	public MenuItem findById(Long id) {
		try {
			return menuRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public MenuItem findByName(String name) {
		try {
			return menuRepository.findByName(name).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public MenuItem findByCategory(String category) {
		try {
			return menuRepository.findByCategory(category).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public MenuItem findByDescription(String description) {
		try {
			return menuRepository.findByDescription(description).get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Override
	public void delete(MenuItem menuItem) {
		menuRepository.delete(menuItem);
	}

	@Override
	public void update(MenuItem menuItem) {
		/*
		 * Optional<MenuItem> updatedMenuItem =
		 * menuRepository.findById(menuItem.getId()); updatedMenuItem = menuItem;
		 * menuRepository.save(updatedMenuItem);
		 */
	}

	@Override
	public String save(MenuItem menuItem) {
		Restaurant rest = restauRepository.findById(menuItem.getRestaurant().getId()).get();
		if(rest != null) {
			menuItem.setRestaurant(rest);
			menuRepository.save(menuItem);
			return ("MenuItem created successfully !");
		}
		return ("We encountered an Error when creating the MenuItem !");
	}

	@Override
	public List<MenuItem> findAll() {
		return (List<MenuItem>) menuRepository.findAll();
	}

}
