package com.mobile.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.JwtTokenMalformedException;
import com.mobile.app.exception.JwtTokenMissingException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.JwtUtil;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.OrderService;

@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@PostMapping("/customer")
	public Customer registerCustomer(@Valid @RequestBody Customer newCustomer) throws CustomerNotFoundException {

		return customerService.addCustomer(newCustomer);

	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException,
			JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.getCustomerById(customerId);
	}

	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable("id") Integer customerId, @Valid @RequestBody Customer customerData,
			HttpServletRequest request) throws CustomerNotFoundException, UserNotFoundException,
			JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.updateCustomer(customerId, customerData);
	}

	@PutMapping("/customer/deactivate/{customerId}")
	public String deactivateCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException,
			JwtTokenMissingException {
		// JwtUtil.validateToken(request);
		return this.customerService.deactivateCustomerAccountById(customerId);

	}

	@PutMapping("/customer/activate/{customerId}")
	public String activateCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException {

		return this.customerService.activateCustomerAccountById(customerId);

	}

	@GetMapping("customer/order/{customerId}")
	public List<Orders> getCustomerOrders(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException,
			JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return this.orderService.getOrderByCustomerId(customerId);
	}

	@PutMapping("/customer/order/{customerId}/{orderId}")
	public String cancelOrderFromCustomerById(@PathVariable("customerId") Integer customerId,
			@PathVariable("orderId") Integer orderId, HttpServletRequest request) throws CustomerNotFoundException,
			OrderNotFoundException{
	
		return customerService.cancelOrdersFromCustomerById(customerId, orderId);
	}

	@GetMapping("customer/orders/order/{orderId}")
	public Orders getCustomerOrdersParticularOrder(@PathVariable("orderId") Integer orderId, HttpServletRequest request)
			throws OrderNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return this.orderService.getOrderById(orderId);
	}

	@PutMapping("/customer/order/{customerId}/{orderId}/{mobileId}")
	public String cancelMobileFromOrderFromCustomerById(@PathVariable("customerId") Integer customerId,
			@PathVariable("orderId") Integer orderId, @PathVariable("mobileId") Integer mobileId,
			HttpServletRequest request) throws CustomerNotFoundException, OrderNotFoundException,
			MobileNotFoundException{

		return customerService.cancelMobileFromOrdersById(customerId, orderId, mobileId);
	}

}
