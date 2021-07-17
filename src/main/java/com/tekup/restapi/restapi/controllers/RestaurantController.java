package com.tekup.restapi.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tekup.restapi.restapi.models.Restaurant;
import com.tekup.restapi.restapi.services.IRestaurantService;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurants")
public class RestaurantController {
	
    @Autowired
    private IRestaurantService restaurantService;
    
    @ResponseBody
    @CrossOrigin("*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> processCreation(@RequestBody Restaurant restaurant, HttpServletRequest request) {

        Map responseMap = new LinkedHashMap<>();

        restaurantService.save(restaurant);
        
        responseMap.put("message", "Restaurant created successfully");
        JSONObject responseObject = new JSONObject(responseMap);
        
        System.out.println(restaurant);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
