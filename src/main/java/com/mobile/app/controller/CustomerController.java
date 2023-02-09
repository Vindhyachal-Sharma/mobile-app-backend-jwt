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

import com.mobile.app.entity.Customer;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

//	-------------------------------------CustomerService------------------------------
//	Customer addCustomer(Customer newCustomer);
//	Customer updateCustomer(Customer updateCustomer) throws CustomerException;
//	Customer deleteCustomerById(Integer customerId) throws CustomerException;
//	Customer getCustomerById(Integer customerId) throws CustomerException;
//	List<Customer> getAllCustomers();

	@PostMapping("/customer")
	public Customer registerCustomer(@RequestBody Customer newCustomer) {

		return customerService.addCustomer(newCustomer);
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Integer customerId) throws CustomerException {

		return customerService.getCustomerById(customerId);
	}

	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer updateCustomer) throws CustomerException {

		return customerService.updateCustomer(updateCustomer);
	}

	@DeleteMapping("/customer/{id}")
	public Customer deleteCustomerById(@PathVariable("id") Integer customerId) throws CustomerException {

		return this.customerService.deleteCustomerById(customerId);

	}

	@GetMapping("/allCustomers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

}
