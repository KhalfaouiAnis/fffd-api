package com.tekup.restapi.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date deliveryDate;

	private String deliveryStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User client;

	@OneToOne
	private Order order;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id", referencedColumnName = "id")
	private Invoice invoice;

	public Delivery() {
	}

	public Delivery(User client, Order order) {
		this.client = client;
		this.order = order;
	}

	public Delivery(User client, Invoice invoice) {
		this.client = client;
		this.invoice = invoice;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", deliveryDate=" + deliveryDate + ", deliveryStatus=" + deliveryStatus
				+ ", client=" + client.getId() + ", order=" + order.getId() + ", invoice=" + invoice + "]";
	}

}
