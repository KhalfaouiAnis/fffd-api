package com.tekup.restapi.app.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 50)
	private String name;

	@Column(length = 250)
	private String description;

	private String image;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "workinghours_id", referencedColumnName = "id")
	private WorkingHours workingHours;

	@OneToMany(mappedBy = "restaurant", cascade = { CascadeType.ALL })
	@JsonManagedReference(value = "user-resto")
	private List<User> managers;

	@OneToMany(mappedBy = "restaurant", cascade = { CascadeType.ALL })
	private List<Review> restaurantReviews;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<MenuItem> menu;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<Order> orders;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public WorkingHours getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(WorkingHours workingHours) {
		this.workingHours = workingHours;
	}

	public List<User> getManagers() {
		return managers;
	}

	public void setManagers(List<User> managers) {
		this.managers = managers;
	}

	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReviews() {
		return restaurantReviews;
	}

	public void setReviews(List<Review> reviews) {
		this.restaurantReviews = reviews;
	}

	public Restaurant() {
	}

	public Restaurant(String name, String description, String image, Category category, Address address,
			WorkingHours workingHours, List<MenuItem> menu, List<User> managers) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.category = category;
		this.address = address;
		this.workingHours = workingHours;
		this.menu = menu;
		this.managers = managers;
	}

	public Restaurant(String name, String description, String image, Category category, Address address,
			WorkingHours workingHours, List<User> managers) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.category = category;
		this.address = address;
		this.workingHours = workingHours;
		this.managers = managers;
	}

	public Restaurant(String name, String description, String image, Category category, Address address,
			WorkingHours workingHours) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.category = category;
		this.address = address;
		this.workingHours = workingHours;
	}

	public Restaurant(String name, String description, String image, Category category, Address address) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.category = category;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", workingHours=" + workingHours + ", name=" + name + ", description="
				+ description + ", address=" + address + "]";
	}

}