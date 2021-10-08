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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.app.models.Order;
import com.tekup.restapi.app.services.IOrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@ResponseBody
	@PostMapping(value = "/placeorder/{restaurantid}")
	public ResponseEntity<?> placeOrderProcess(@RequestBody Order order, @PathVariable int restaurantid) {
		try {
			orderService.placeOrder(order, restaurantid);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Order placed successfully", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/findbyid/{orderid}")
	public ResponseEntity<?> getOrderByIdProcess(@PathVariable int orderid) {
		Order order;
		try {
			order = orderService.findById(orderid);
			if (order == null) {
				return new ResponseEntity<String>("Order does not exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/todayresto/{restoid}")
	public ResponseEntity<?> getRestoTodaysOrdersProcess(@PathVariable int restoid) {
		Map<String, String> responseMap = new LinkedHashMap<>();
		List<Order> orders;
		try {
			orders = orderService.getTodayOrdersByRestoId(restoid);
			if (orders.isEmpty()) {
				responseMap.put("message", "No orders yet for today");
				JSONObject responseObject = new JSONObject(responseMap);
				return new ResponseEntity<JSONObject>(responseObject, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/allRestoOrders/{restoid}")
	public ResponseEntity<?> getRestoAllOrdersProcess(@PathVariable int restoid) {
		Map<String, String> responseMap = new LinkedHashMap<>();
		List<Order> orders;
		try {
			orders = orderService.getByRestaurantId(restoid);
			if (orders.isEmpty()) {
				responseMap.put("message", "No orders found !");
				JSONObject responseObject = new JSONObject(responseMap);
				return new ResponseEntity<JSONObject>(responseObject, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getClientOrdersProcess(@PathVariable int id) {
		Map<String, String> responseMap = new LinkedHashMap<>();
		List<Order> orders;
		try {
			orders = orderService.findByCustomerId(id);
			if (orders.isEmpty()) {
				responseMap.put("message", "No orders yet");
				JSONObject responseObject = new JSONObject(responseMap);
				return new ResponseEntity<JSONObject>(responseObject, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/deliver/{id}")
	public ResponseEntity<?> deliverOrderProcess(@PathVariable int id) {
		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			boolean deliver = orderService.deliverOrder(id);
			if (!deliver) {
				responseMap.put("message", "Error accured while Delivering this order !");
				JSONObject responseObject = new JSONObject(responseMap);
				return new ResponseEntity<JSONObject>(responseObject, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		responseMap.put("message", "Order delivered successfully !");
		JSONObject responseObject = new JSONObject(responseMap);
		return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
	}

}
