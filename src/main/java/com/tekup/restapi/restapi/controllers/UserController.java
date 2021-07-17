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

import com.tekup.restapi.restapi.models.User;
import com.tekup.restapi.restapi.services.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private IUserService userService;
    
    @ResponseBody
    @CrossOrigin("*")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> processRegistration(@RequestBody User user, HttpServletRequest request) {

        Map responseMap = new LinkedHashMap<>();

        // Lookup user in database by e-mail
        User userExists_byemail = userService.findByEmail(user.getEmail());

        if (userExists_byemail != null) {
            responseMap.put("message", "already exists");
            JSONObject responseObject = new JSONObject(responseMap);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }

        userService.register(user);
        responseMap.put("message", "created successfully");
        JSONObject responseObject = new JSONObject(responseMap);

        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

}
