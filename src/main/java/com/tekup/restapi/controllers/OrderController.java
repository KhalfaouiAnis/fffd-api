package com.tekup.restapi.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.models.Order;
import com.tekup.restapi.models.Restaurant;
import com.tekup.restapi.services.IOrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@ResponseBody
	@CrossOrigin("*")
	@RequestMapping(value = "/placeorder", method = RequestMethod.POST)
	public ResponseEntity<?> placeOrderProcess(@RequestBody Order order) {
		System.out.println("controller order: " + order);
		try {
			orderService.placeOrder(order);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Order placed successfully", HttpStatus.OK);
	}

	@ResponseBody
	@CrossOrigin("*")
	@RequestMapping(value = "/findbyid/{orderId}", method = RequestMethod.POST)
	public ResponseEntity<?> getRestaurantByIdProcess(@PathVariable int orderId) {
		Order order;
		try {
			order = orderService.findById(orderId);
			if (order != null) {
				System.out.println("order:  " + order);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
}
