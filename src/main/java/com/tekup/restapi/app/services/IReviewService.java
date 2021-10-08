package com.tekup.restapi.app.services;

import com.tekup.restapi.app.models.Review;

public interface IReviewService {
	public Review findByUserAndRestaurant(int userId, int restaurantId);

	public void makeReview(Review review) throws Exception;
	
	public Review findById(int id);

}
