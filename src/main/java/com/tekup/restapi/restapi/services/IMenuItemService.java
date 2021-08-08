package com.tekup.restapi.restapi.services;

import com.tekup.restapi.restapi.models.MenuItem;

public interface IMenuItemService {
	MenuItem findMenuItemById(Long id);
	MenuItem findMenuItemByName(String name);
	MenuItem findMenuItemByCategory(String category);
	MenuItem findMenuItemByDescription(String description);
	
	public void delete(MenuItem menuItem);
	public void update(MenuItem menuItem);
	public void save(MenuItem menuItem);
}
