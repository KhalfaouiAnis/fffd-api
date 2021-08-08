package com.tekup.restapi.restapi.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private Date workingHours;

	@Column(length = 50)
	private String name;

	@Column(length = 250)
	private String description;

	@Column(length = 50)
	private String address;

	// chefs
    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
	private List<User> users;

	// menu
    @Transient
    private List<MenuItem> todaysMenu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHeureTravail() {
		return workingHours;
	}

	public void setHeureTravail(Date workingHours) {
		this.workingHours = workingHours;
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

	public Restaurant() {
	}

	public Restaurant(String name, String description, String address) {
		this.name = name;
		this.description = description;
		this.address = address;
	}

}