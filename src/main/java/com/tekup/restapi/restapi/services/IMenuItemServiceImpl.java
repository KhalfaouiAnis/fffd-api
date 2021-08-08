package com.tekup.restapi.restapi.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.restapi.models.MenuItem;
import com.tekup.restapi.restapi.repositories.MenuItemRepository;

@Service
public class IMenuItemServiceImpl implements IMenuItemService {
	
	@Autowired
	MenuItemRepository menuRepository;

	@Override
	public MenuItem findMenuItemById(Long id) {
		try {
			return menuRepository.findMenuItemById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public MenuItem findMenuItemByName(String name) {
		try {
			return menuRepository.findMenuItemByName(name).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public MenuItem findMenuItemByCategory(String category) {
		try {
			return menuRepository.findMenuItemByCategory(category).get();
		} catch (NoSuchElementException e) {
			return null;
		}		
	}

	@Override
	public MenuItem findMenuItemByDescription(String description) {
		try {
			return menuRepository.findMenuItemByDescription(description).get();
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
	public void save(MenuItem menuItem) {
		menuRepository.save(menuItem);
		
	}

}
