package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;

public interface OrderService {

	// CRUD
	Orders addOrder(Orders newOrder);

	String cancelOrderById(Integer orderId) throws OrderNotFoundException;

	Orders getOrderById(Integer orderId) throws OrderNotFoundException;

	Orders addOrderToCustomer(Orders Order, Integer customerd) throws CustomerNotFoundException;

	List<Orders> getAllOrders();

	Orders getOrdersFromCart(Payment payment,Integer cartId) throws CartNotFoundException, CustomerNotFoundException;

	List<Orders> getOrderByCustomerId(Integer customerId) throws CustomerNotFoundException;

}
