package com.tekup.restapi.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "workinghours")
public class WorkingHours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 8)
	private String openingHour;
	@Column(length = 8)
	private String closingHour;

	@OneToOne(mappedBy = "workingHours", cascade = CascadeType.ALL)
	@JsonBackReference
	private Restaurant restaurant;

	public WorkingHours(String openingHour, String closingHour) {
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}

	public WorkingHours() {
	}

	public String getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}

	public String getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(String closingHour) {
		this.closingHour = closingHour;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "WorkingHours [openingHour=" + openingHour + ", closingHour=" + closingHour + "]";
	}

}
