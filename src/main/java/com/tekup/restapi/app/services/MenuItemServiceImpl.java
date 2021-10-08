package com.tekup.restapi.app.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.app.models.MenuItem;
import com.tekup.restapi.app.models.Restaurant;
import com.tekup.restapi.app.repositories.MenuItemRepository;
import com.tekup.restapi.app.repositories.RestaurantRepository;

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
	public void update(MenuItem menuItem, Long menuId) {
		MenuItem menuItm = menuRepository.findById(menuId).get();
		
		if (menuItem.getName() != null) {
			menuItm.setName(menuItem.getName());
		}
		if (menuItem.getDescription() != null) {
			menuItm.setDescription(menuItem.getDescription());
		}
		if (menuItem.getPrice() != 0) {
			menuItm.setPrice(menuItem.getPrice());
		}
		if (menuItem.getImage() != null) {
			menuItm.setImage(menuItem.getImage());
		}
		if (!menuItem.getImages().isEmpty()) {
			menuItm.setImages(menuItem.getImages());
		}
		menuItm.setAvailable(menuItem.isAvailable());
		
		menuRepository.save(menuItm);

	}

	@Override
	public void save(MenuItem menuItem) throws Exception {
		try {
			Restaurant rest = restauRepository.findById(menuItem.getRestaurant().getId()).get();
			if (rest != null) {
				menuItem.setRestaurant(rest);
				menuRepository.save(menuItem);
			}
		} catch (Exception e) {
			throw new Exception("MenuItem creation failed !");
		}
	}

	@Override
	public List<MenuItem> findAll() {
		return (List<MenuItem>) menuRepository.findAll();
	}

	@Override
	public List<MenuItem> findMenusByRestaurantId(int restaurantId) {
		return menuRepository.findMenusByRestaurantId(restaurantId).get();
	}

}
