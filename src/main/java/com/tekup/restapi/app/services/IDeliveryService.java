package com.tekup.restapi.app.services;

import com.tekup.restapi.app.models.Delivery;
import com.tekup.restapi.app.models.Order;

public interface IDeliveryService {
	Delivery findById(int id);
	void create (Order o);
	void setStatus(int d, String status);
}
