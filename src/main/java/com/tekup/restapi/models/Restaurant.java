package com.tekup.restapi.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Transient
	private WorkingHours workingHours;

	@Column(length = 50)
	private String name;

	@Column(length = 250)
	private String description;

	@Column(length = 50)
	private String address;

	// restaurant managers
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "restaurant-managers")
	private List<User> managers;

	// menu
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "restaurant-menu")
	private List<MenuItem> menu;
	
	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	 * 
	 * @JoinTable(name = "reviews", joinColumns = @JoinColumn(name =
	 * "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	 * 
	 * @JsonManagedReference private Set<Review> reviews;
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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

	public Restaurant() {
	}

	public Restaurant(String name, String description, String address, List<MenuItem> menu, List<User> managers) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.menu = menu;
		this.managers = managers;
	}

	public Restaurant(String name, String description, String address, WorkingHours workingHours) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.workingHours = workingHours;
	}

	public Restaurant(String name, String description, String address) {
		this.name = name;
		this.description = description;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", workingHours=" + workingHours + ", name=" + name + ", description="
				+ description + ", address=" + address + "]";
	}

}