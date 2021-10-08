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

import com.tekup.restapi.app.models.Review;
import com.tekup.restapi.app.services.IReviewService;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private IReviewService reviewService;

	@ResponseBody
	@PostMapping(value = "/create-review")
	public ResponseEntity<?> processReviewCreation(@RequestBody Review review) {
		Map<String, String> responseMap = new LinkedHashMap<>();

		try {
			reviewService.makeReview(review);
			responseMap.put("message", "Your review is saved !");
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.OK);
		} catch (Exception e) {
			responseMap.put("message", e.getMessage());
			JSONObject responseObject = new JSONObject(responseMap);
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@GetMapping("/findbyid/{reviewId}")
	public ResponseEntity<?> getReviewByIdProcess(@PathVariable int reviewId) {
		Review reveiw;
		try {
			reveiw = reviewService.findById(reviewId);
			if (reveiw == null) {
				return new ResponseEntity<String>("Review does not exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println("error: " + e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Review>(reveiw, HttpStatus.OK);
	}

}
