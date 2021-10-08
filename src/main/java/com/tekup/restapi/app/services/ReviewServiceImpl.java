package com.tekup.restapi.app.services;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.app.models.Restaurant;
import com.tekup.restapi.app.models.Review;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.repositories.RestaurantRepository;
import com.tekup.restapi.app.repositories.ReviewRepository;
import com.tekup.restapi.app.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	RestaurantRepository restauRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ReviewRepository reviewRepo;

	@Override
	public Review findByUserAndRestaurant(int userId, int restaurantId) {
		try {
			return reviewRepo.findByUserIdAndRestaurantId(userId, restaurantId).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public Review findById(int id) {
		return reviewRepo.findById(id);
	}

	@Override
	public void makeReview(Review review) throws Exception {
		User user = userRepo.findById(review.getUser().getId()).get();
		Restaurant resto = restauRepo.findById(review.getRestaurant().getId()).get();
		Review reviewExist = findByUserAndRestaurant(user.getId(), resto.getId());
		if (reviewExist != null) {
			throw new Exception("You have already rated this restaurant !");
		}
		review.setReviewDate(new Date());
		review.setUser(user);
		review.setRestaurant(resto);
		
		if (review.getComment() == null) {
			Review newReview = new Review(review.getRating(), review.getUser(), review.getRestaurant());
			newReview.setReviewDate(new Date());
			reviewRepo.save(newReview);
		} else {
			reviewRepo.save(review);
		}
	}

}
