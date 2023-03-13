package com.mobile.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.JwtTokenMalformedException;
import com.mobile.app.exception.JwtTokenMissingException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.JwtUtil;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.OrderService;

@RestController
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/order/{id}")
	public Orders getOrderById(@PathVariable("id") Integer orderId) throws OrderNotFoundException {

		return orderService.getOrderById(orderId);
	}

	@DeleteMapping("/cancel/order/{customerId}/{orderId}")
	public String CancelOrderById(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId,HttpServletRequest request)
			throws OrderNotFoundException, CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return this.customerService.cancelOrdersFromCustomerById(customerId, orderId);

	}

	@GetMapping("/orders/")
	public List<Orders> getallOrders(HttpServletRequest request) throws UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return orderService.getAllOrders();
	}
}
