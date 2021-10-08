package com.tekup.restapi.app.services;

import java.util.List;

import com.tekup.restapi.app.models.MenuItem;

public interface IMenuItemService {
	MenuItem findById(Long id);
	MenuItem findByName(String name);
	MenuItem findByDescription(String description);	
	List<MenuItem> findAll();
	List<MenuItem> findMenusByRestaurantId(int restaurantId);
	
	void delete(MenuItem menuItem);
	void update(MenuItem menuItem, Long menuId);
	void save(MenuItem menuItem) throws Exception;
}
