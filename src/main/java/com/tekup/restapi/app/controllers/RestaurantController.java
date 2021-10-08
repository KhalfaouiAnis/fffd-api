package com.tekup.restapi.app.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.app.models.Restaurant;
import com.tekup.restapi.app.services.IRestaurantService;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private IRestaurantService restaurantService;

	@ResponseBody
	@PostMapping(value = "/save")
	public ResponseEntity<?> processCreation(@RequestBody Restaurant restaurant) {
		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			restaurantService.save(restaurant);
			responseMap.put("message", "Restaurant created successfully");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> processUpdate(@PathVariable int id, @RequestBody Restaurant restaurant) {
		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			restaurantService.update(id, restaurant);
			responseMap.put("message", "Restaurant updated successfully");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> processDelete(@RequestBody Restaurant restaurant) {
		try {
			restaurantService.delete(restaurant);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Restaurant deleted", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "/findbyid/{restaurantId}")
	public ResponseEntity<?> getRestaurantByIdProcess(@PathVariable int restaurantId) {
		Restaurant rest;
		try {
			rest = restaurantService.getById(restaurantId);
			if (rest == null) {
				return new ResponseEntity<String>("Restaurant does not exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "/findbymanagerid/{managerId}")
	public ResponseEntity<?> getRestaurantByManagerIdProcess(@PathVariable int managerId) {
		Restaurant rest;
		try {
			rest = restaurantService.getByManagerId(managerId);
			if (rest == null) {
				return new ResponseEntity<String>("Restaurant does not exist", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getallrestaurants", produces = "application/json")
	public ResponseEntity<?> getAllRestaurants() {
		List<Restaurant> rests = restaurantService.findAll();
		return new ResponseEntity<List<Restaurant>>(rests, HttpStatus.OK);
	}

	@GetMapping(value = "/getfeaturedrestaurants", produces = "application/json")
	public ResponseEntity<?> getFeaturedRestaurants() {
		List<Restaurant> rests = restaurantService.getFeaturedRestaurants();
		return new ResponseEntity<List<Restaurant>>(rests, HttpStatus.OK);
	}
}
