package com.tekup.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.models.Restaurant;
import com.tekup.restapi.services.IRestaurantService;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private IRestaurantService restaurantService;

	@ResponseBody
	@CrossOrigin("*")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> processCreation(@RequestBody Restaurant restaurant) {

		Map responseMap = new LinkedHashMap<>();
		JSONObject responseObject = new JSONObject(responseMap);
		try {
			restaurantService.save(restaurant);
			responseMap.put("message", "Restaurant created successfully");
			System.out.println(restaurant);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			System.out.println("responseMap: "+ responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Restaurant created successfully", HttpStatus.OK);
	}

	@ResponseBody
	@CrossOrigin("*")
	@RequestMapping(value = "/findbyid/{restaurantId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getRestaurantByIdProcess(@PathVariable Integer restaurantId) {
		Map responseMap  = new LinkedHashMap<>();
		Restaurant rest;
		try {
			rest = restaurantService.findById(restaurantId);
			if (rest != null) {
				responseMap.put("restaurant", rest);
				System.out.println("rest:  " + rest);
				System.out.println("responseMap:  " + responseMap);
			}
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			return new ResponseEntity<JSONObject>(new JSONObject(responseMap), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllRestaurants() {
		List<Restaurant> rests = restaurantService.findAll();
		return new ResponseEntity<List<Restaurant>>(rests, HttpStatus.OK);
	}

}
