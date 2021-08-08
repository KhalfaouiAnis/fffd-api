package com.tekup.restapi.restapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private int price;
	private String category;
	@Column(length = 64)
	private String image;
	private boolean available;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public MenuItem() {
		this.name = "";
		this.description = "";
		this.price = 0;
		this.category = "";
		this.image = "";
		this.available = true;
	}

	public MenuItem(String name, String description, int price, String image) {
		this.available = true;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
	}

	public MenuItem(String name, String description, int price, String category, String image, boolean available) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
		this.available = available;
	}

}
