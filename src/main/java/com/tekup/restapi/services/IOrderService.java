package com.tekup.restapi.services;

import com.tekup.restapi.models.Order;

public interface IOrderService {
	Order findById(int id);

	Order findByUserId(int id);

	void setDeliveryStatus(int orderId, boolean newStatus);

	void placeOrder(Order order) throws NoSuchFieldException;
}
