package com.tekup.restapi.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value = "orders")
	private User user;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "orders_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
	private List<MenuItem> orderItems;

	private Date orderDate;
	private boolean deliveryStatus;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<MenuItem> getItems() {
		return orderItems;
	}

	public void setItems(List<MenuItem> items) {
		this.orderItems = items;
	}

	public boolean getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public int getId() {
		return id;
	}

	public Order(List<MenuItem> items, boolean deliveryStatus, User user) {
		this.orderItems = items;
		this.deliveryStatus = deliveryStatus;
		this.user = user;
	}

	public Order(Date orderDate, boolean deliveryStatus) {
		this.orderDate = orderDate;
		this.deliveryStatus = deliveryStatus;
	}

	public Order() {
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", items=" + orderItems + ", deliveryStatus="
				+ deliveryStatus + "]";
	}
}
