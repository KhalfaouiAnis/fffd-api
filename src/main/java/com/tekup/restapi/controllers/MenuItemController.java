package com.tekup.restapi.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.models.MenuItem;
import com.tekup.restapi.services.IMenuItemService;

@RestController
@CrossOrigin("*")
@RequestMapping("/menu")
public class MenuItemController {

	@Autowired
	private IMenuItemService menuService;

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addNewMenuItem(@RequestBody MenuItem menu, HttpServletRequest request) {

		String response = menuService.save(menu);
		if (!response.equals("MenuItem created successfully !")) {
			return new ResponseEntity<>("MenuItem creation failed !", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("MenuItem created successfully !", HttpStatus.OK);
	}

}
