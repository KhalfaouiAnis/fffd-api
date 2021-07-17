package com.tekup.restapi.restapi.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50)
	private Date creationDate;
	
	@Column(length = 50)
	private Date heureTravail;

	@Column(length = 50)
	private String name;	
	
	@Column(length = 250)
	private String description;
	
	@Column(length = 50)
	private String address;
	
	
	//chef Restau
	
	
	//liste des plat


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getHeureTravail() {
		return heureTravail;
	}


	public void setHeureTravail(Date heureTravail) {
		this.heureTravail = heureTravail;
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
	
	public Restaurant () {}


	public Restaurant(String name, String description, String address) {
		this.name = name;
		this.description = description;
		this.address = address;
	}


	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", creationDate=" + creationDate + ", heureTravail=" + heureTravail + ", name="
				+ name + ", description=" + description + ", address=" + address + "]";
	}
	
}