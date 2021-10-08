package com.tekup.restapi.app.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JsonBackReference
	private Restaurant restaurant;
	
	@OneToOne
	private Delivery delivery;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "orders_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
	private List<MenuItem> items;

	private Date orderDate;

	private String orderStatus;

	private int subtotal;

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

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order(List<MenuItem> items, User user, Restaurant restaurant) {
		this.items = items;
		this.user = user;
		this.restaurant = restaurant;
	}

	public Order(User user, List<MenuItem> items, Restaurant restaurant, int subtotal) {
		this.user = user;
		this.items = items;
		this.restaurant = restaurant;
		this.subtotal = subtotal;
	}
	
	public Order(User user, List<MenuItem> items, int subtotal) {
		this.user = user;
		this.items = items;
		this.subtotal = subtotal;
	}

	public Order(User user, Restaurant restaurant, List<MenuItem> items, int subtotal) {
		this.items = items;
		this.user = user;
		this.restaurant = restaurant;
		this.subtotal = subtotal;
	}

	public Order() {
		this.orderStatus = "Pending";
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderItems=" + items + ", subtotal=" + subtotal + "]";
	}
}
