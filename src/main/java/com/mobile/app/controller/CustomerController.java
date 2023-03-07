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
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
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
			throws CustomerNotFoundException, UserNotFoundException {

		return customerService.getCustomerById(customerId);
	}

	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable("id") Integer customerId,@Valid @RequestBody Customer customerData, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException {

		return customerService.updateCustomer(customerId,customerData);
	}
//	@PutMapping("/customer/")
//	public Customer updateCustomer(@Valid @RequestBody Customer customerData, HttpServletRequest request)
//			throws CustomerNotFoundException, UserNotFoundException {
//			
//		return customerService.updateCustomer(customerData);
//	}

	@PutMapping("/customer/account/{customerId}")
	public String deactivateCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException {
//		login.validateToken(request,"customer");
		return this.customerService.deactivateCustomerAccountById(customerId);

	}
	@GetMapping("customer/order/{customerId}")
	public List<Orders> getCustomerOrders(@PathVariable("customerId") Integer customerId) throws CustomerNotFoundException{
		return this.orderService.getOrderByCustomerId(customerId);
	}
	
	@PutMapping("/customer/order/{customerId}/{orderId}")
	public String cancelOrderFromCustomerById(@PathVariable("customerId") Integer customerId,@PathVariable("orderId")Integer orderId)
			throws CustomerNotFoundException, UserNotFoundException, OrderNotFoundException {

		return customerService.cancelOrdersFromCustomerById(customerId,orderId);
	}

//	@GetMapping("/allCustomers")
//	public List<Customer> getAllCustomers() {
//		return customerService.getAllCustomers();
//	}

}
