package com.tekup.restapi.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private float subtotal;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference(value = "delivery-invoice")
	private Delivery delivery;

	public Invoice() {
	}

	public Invoice(Delivery delivery, float subtotal) {
		this.delivery = delivery;
		this.subtotal = subtotal;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", subtotal=" + subtotal + "]";
	}
}
