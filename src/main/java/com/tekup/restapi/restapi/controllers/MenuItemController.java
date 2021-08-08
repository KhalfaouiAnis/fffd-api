package com.tekup.restapi.restapi.controllers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.restapi.models.MenuItem;
import com.tekup.restapi.restapi.models.User;
import com.tekup.restapi.restapi.repositories.MenuItemRepository;
import com.tekup.restapi.restapi.repositories.RestaurantRepository;
import com.tekup.restapi.restapi.services.IMenuItemService;
import com.tekup.restapi.restapi.services.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/menu")
public class MenuItemController {
	
	@Autowired
    private IMenuItemService menuService;
	
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> addNewMenuItem(@RequestBody MenuItem menu, HttpServletRequest request) {
    	System.out.println("*******************");
    	System.out.println(menu);

        Map responseMap = new LinkedHashMap<>();

        // Lookup user in database by e-mail
		/*
		 * MenuItem menuExist = menuService.findMenuItemByName(menu.getName());
		 * 
		 * if (menuExist.getName() != "") { responseMap.put("message",
		 * "already exists"); JSONObject responseObject = new JSONObject(responseMap);
		 * return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST); }
		 */
		 
		menuService.save(menu);
        responseMap.put("message", "created successfully");
        JSONObject responseObject = new JSONObject(responseMap);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
