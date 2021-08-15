package com.tekup.restapi.services;

import java.util.List;

import com.tekup.restapi.models.MenuItem;

public interface IMenuItemService {
	MenuItem findById(Long id);
	MenuItem findByName(String name);
	MenuItem findByCategory(String category);
	MenuItem findByDescription(String description);	
	List<MenuItem> findAll();
	
	void delete(MenuItem menuItem);
	void update(MenuItem menuItem);
	String save(MenuItem menuItem);
}
