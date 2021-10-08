package com.tekup.restapi.app.controllers;

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

import com.tekup.restapi.app.models.Delivery;
import com.tekup.restapi.app.models.Order;
import com.tekup.restapi.app.services.IDeliveryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	private IDeliveryService deliveryService;

	@ResponseBody
	@PostMapping(value = "/create")
	public ResponseEntity<?> placeOrderProcess(@RequestBody Order order) {
		try {
			deliveryService.create(order);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Delivery created successfully", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/findbyid/{deliveryId}")
	public ResponseEntity<?> getOrderByIdProcess(@PathVariable int deliveryId) {
		Delivery delivery;
		try {
			delivery = deliveryService.findById(deliveryId);
			if (delivery == null) {
				return new ResponseEntity<String>("Delivery does not exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Delivery>(delivery, HttpStatus.OK);
	}
	
}
