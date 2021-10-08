package com.tekup.restapi.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 20)
	private Date creationDate;

	@Column(length = 50, nullable = false)
	private String firstName;

	@Column(length = 50, nullable = false)
	private String lastName;

	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@Column(name = "password", length = 64, nullable = false)
	private String password;

	@Column(length = 15)
	private String phoneNumber;

	@Column(length = 50)
	private String address;

	@Column(length = 45)
	private String profileImage;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JsonBackReference(value = "user-resto")
	private Restaurant restaurant;

	@OneToMany(cascade = { CascadeType.ALL })
	@JsonBackReference(value = "user-deliveries")
	private List<Delivery> deliveries;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	@JsonBackReference(value = "user-orders")
	private List<Order> orders;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Review> userReviews;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImg) {
		this.profileImage = profileImg;
	}

	public int getId() {
		return id;
	}

	public List<Role> getRoles() {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Review> getReviews() {
		return userReviews;
	}

	public void setReviews(List<Review> reviews) {
		this.userReviews = reviews;
	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(String firstName, String lastName, String email, String password, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(String firstName, String lastName, String email, String password, List<Role> roles,
			Restaurant restaurant) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.restaurant = restaurant;
	}

	public User(String firstName, String lastName, String email, String password, String phoneNumber, String address,
			Restaurant restaurant, List<Role> roles, String profileImage) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.restaurant = restaurant;
		this.roles = roles;
		this.profileImage = profileImage;
	}

	public User() {
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", creationDate=" + creationDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", roles=" + roles + "]";
	}

}
