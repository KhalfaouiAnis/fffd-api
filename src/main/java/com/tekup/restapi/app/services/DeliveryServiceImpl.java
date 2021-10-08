package com.tekup.restapi.app.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.restapi.app.models.Delivery;
import com.tekup.restapi.app.models.Invoice;
import com.tekup.restapi.app.models.Order;
import com.tekup.restapi.app.models.User;
import com.tekup.restapi.app.repositories.DeliveryRepository;
import com.tekup.restapi.app.repositories.InvoiceRepository;
import com.tekup.restapi.app.repositories.OrderRepository;
import com.tekup.restapi.app.repositories.UserRepository;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

	@Autowired
	DeliveryRepository deliveryRepo;

	@Autowired
	InvoiceRepository invoiceRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public Delivery findById(int id) {
		try {
			return deliveryRepo.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void create(Order o) {
		Order order = orderRepo.findById(o.getId());
		System.out.println("order: " + order);
		User client = userRepo.findById(order.getUser().getId()).get();

		Delivery d = new Delivery(client, order);
		Invoice i = new Invoice(d, order.getSubtotal());
		// i.setSubtotal(o.getSubtotal());
		d.setInvoice(i);
		// i.setDelivery(d);
		d.setDeliveryDate(new Date());

		System.out.println("order: " + order);
		deliveryRepo.save(d);
		invoiceRepo.save(i);
	}

	@Override
	public void setStatus(int d, String status) {
		Delivery del = deliveryRepo.findById(d).get();
		del.setDeliveryStatus(status);
		deliveryRepo.save(del);
	}

}
