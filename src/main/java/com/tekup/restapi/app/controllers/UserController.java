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

import com.tekup.restapi.app.models.Role;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.services.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@ResponseBody
	@PutMapping(value = "/update/{userId}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int userId) {

		Map<String, String> responseMap = new LinkedHashMap<>();
		try {
			userService.update(user, userId);
			responseMap.put("message", "User account updated successfully !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<JSONObject>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/{userid}/addrole")
	public ResponseEntity<String> addRoleProcess(@RequestBody String roleName, @PathVariable int userid) {
		try {
			userService.addRole(userid, roleName);
			return new ResponseEntity<String>("Role added to user roles", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error accured", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/{userid}/removerole")
	public ResponseEntity<String> removeRoleProcess(@RequestBody Role role, @PathVariable int userid) {
		try {
			userService.removeRole(userid, role);
			return new ResponseEntity<String>("Role removed from user roles", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error accured", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/allusers")
	public ResponseEntity<?> getUsersProcess() {
		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error accured !", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/findbyid/{userId}")
	public ResponseEntity<?> getUserByIdProcess(@PathVariable int userId) {
		try {
			User user = userService.findById(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("User not found !", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/findbyroleName/{roleName}")
	public ResponseEntity<?> getUserByRoleNameProcess(@PathVariable String roleName) {
		try {
			List<User> users = userService.findByRoleName(roleName);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("no users found !", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/deleteuser")
	public ResponseEntity<?> deleteUserProcess(@RequestBody User user) {
		try {
			userService.deleteUser(user);
			return new ResponseEntity<>("User deleted !", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("User not found !", HttpStatus.BAD_REQUEST);
		}
	}
}
