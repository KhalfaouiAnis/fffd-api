package com.tekup.restapi.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "reviews")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = { CascadeType.ALL })
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	@JsonBackReference
	private Restaurant restaurant;

	private Date reviewDate;

	@Column(length = 250)
	private String comment;

	private float rating;

	public Review(String comment, float rating, User user, Restaurant restaurant) {
		this.comment = comment;
		this.rating = rating;
		this.user = user;
		this.restaurant = restaurant;
	}

	public Review(float rating, User user, Restaurant restaurant) {
		this.rating = rating;
		this.user = user;
		this.restaurant = restaurant;
	}

	public Review() {

	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", user=" + user.getFirstName() + ", restaurant=" + restaurant.getName()
				+ ", reviewDate=" + reviewDate + ", comment=" + comment + ", rating=" + rating + "]";
	}

}
