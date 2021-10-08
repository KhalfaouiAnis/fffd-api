package com.tekup.restapi.app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.app.models.MenuItem;
import com.tekup.restapi.app.services.IMenuItemService;

@RestController
@CrossOrigin("*")
@RequestMapping("/menu")
public class MenuItemController {

	@Autowired
	private IMenuItemService menuService;

	@ResponseBody
	@PostMapping(value = "/save")
	public ResponseEntity<?> addNewMenuItem(@RequestBody MenuItem menu) {

		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			menuService.save(menu);
			responseMap.put("message", "MenuItem created successfully !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@PutMapping(value = "/update/{menuItemId}")
	public ResponseEntity<?> updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable Long menuItemId) {

		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			menuService.update(menuItem, menuItemId);
			responseMap.put("message", "MenuItem updated successfully !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteMenuItem(@RequestBody MenuItem menu) {

		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			menuService.delete(menu);
			responseMap.put("message", "MenuItem deleted successfully !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@GetMapping("/findbyid/{menuItemId}")
	public ResponseEntity<?> getMenuItemByIdProcess(@PathVariable Long menuItemId) {
		MenuItem menuItem;
		try {
			menuItem = menuService.findById(menuItemId);
			if (menuItem == null) {
				return new ResponseEntity<String>("No menu found !", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<MenuItem>(menuItem, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/findbyrestaurantid/{restaurantId}")
	public ResponseEntity<?> getMenuItemByRestaurantIdProcess(@PathVariable int restaurantId) {
		List<MenuItem> menuItems;
		try {
			menuItems = menuService.findMenusByRestaurantId(restaurantId);
			if (menuItems.isEmpty()) {
				return new ResponseEntity<String>("No menus found for this restaurant", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<MenuItem>>(menuItems, HttpStatus.OK);
	}

}
