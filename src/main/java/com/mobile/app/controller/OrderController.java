package com.mobile.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/order/{id}")
	public Orders getOrderById(@PathVariable("id") Integer orderId) throws OrderNotFoundException {

		return orderService.getOrderById(orderId);
	}

	@DeleteMapping("/cancel/order/{orderId}")
	public String CancelOrderById(@RequestBody Integer customerId, @PathVariable("orderId") Integer orderId)
			throws OrderNotFoundException, CustomerNotFoundException {

		return this.customerService.cancelOrdersFromCustomerById(customerId, orderId);

	}

	@GetMapping("/orders/")
	public List<Orders> getallOrders() {
		return orderService.getAllOrders();
	}
}
