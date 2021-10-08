package com.tekup.restapi.app.models;

import java.io.Serializable;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30)
	private String name;
	private String description;
	@Column(length = 2)
	private int price;
	private boolean available;
	@Column(length = 45)
	private String image;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "menuitem_images", joinColumns = @JoinColumn(name = "menuitem_id"), inverseJoinColumns = @JoinColumn(name = "menuitemimage_id"))
	private List<MenuItemImage> images;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Restaurant restaurant;

	public MenuItem() {
		this.name = "";
		this.description = "";
		this.price = 0;
		this.image = null;
		this.images = null;
		this.available = true;
		this.restaurant = null;
	}

	public MenuItem(String name, String description, int price, String image, List<MenuItemImage> images,
			Restaurant restaurant) {
		this.available = true;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.images = images;
		this.restaurant = restaurant;
	}

	public MenuItem(boolean available, String name, String description, int price, String image, Restaurant restaurant) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.available = available;
		this.restaurant = restaurant;
	}

	public MenuItem(String name, String description, int price, String image, List<MenuItemImage> images,
			boolean available, Restaurant restaurant) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.images = images;
		this.available = available;
		this.restaurant = restaurant;
	}

	public Long getId() {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<MenuItemImage> getImages() {
		return images;
	}

	public void setImages(List<MenuItemImage> images) {
		this.images = images;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", image=" + image + ", available=" + available + "]";
	}

}
