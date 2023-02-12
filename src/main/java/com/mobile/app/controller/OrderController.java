package com.mobile.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.OrderException;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;

	// ---------------------------------------------Services

//	Orders addOrder(Orders newOrder);
//	String updateOrder(Orders order, Integer id);
//	Orders deleteOrderById(Integer orderId) throws OrderException;
//	Orders getOrderById(Integer orderId) throws OrderException;
//	Orders addOrderToCustomer(Orders Order, Integer id) throws CustomerException;
//	// double calculateTotalCost(List<Mobile> list);
//	List<Orders> getAllOrders();

	@PostMapping("/order")
	public Orders addOrder(@RequestBody Orders newOrder) throws OrderException {

		return orderService.addOrder(newOrder);
	}

	@PostMapping("/order/{id}")
	public Orders addOrderToCustomer(@RequestBody Orders order, @PathVariable("id") Integer id) throws CustomerException {
		return this.orderService.addOrderToCustomer(order, id);
	}

	@GetMapping("/order/{id}")
	public Orders getOrderById(@PathVariable("id") Integer orderId) throws OrderException {

		return orderService.getOrderById(orderId);
	}

	@PutMapping("/order")
	public String updateOrder(@RequestBody Orders order) {

		return orderService.updateOrder(order);
	}

	@DeleteMapping("/order/{id}")
	public String deleteOrderById(@RequestBody Integer customerId, @PathVariable("id") Integer orderId)
			throws OrderException, CustomerException {

		return this.customerService.deleteOrdersFromCustomerById(customerId,orderId);

	}

	@GetMapping("/allorders/")
	public List<Orders> getallOrders() {
		return orderService.getAllOrders();
	}
}
