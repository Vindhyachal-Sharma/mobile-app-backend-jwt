package com.mobile.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Customer;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.ValidateToken;
import com.mobile.app.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	ValidateToken login;
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	public Customer registerCustomer(@Valid @RequestBody Customer newCustomer) throws CustomerNotFoundException{
		return customerService.addCustomer(newCustomer);
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Integer customerId, HttpServletRequest request) throws CustomerNotFoundException, UserNotFoundException {
		login.validateToken(request,"Admin");
		return customerService.getCustomerById(customerId);
	}

	@PutMapping("/customer")
	public Customer updateCustomer(@Valid @RequestBody Customer updateCustomer, HttpServletRequest request) throws CustomerNotFoundException, UserNotFoundException {
		login.validateToken(request,"customer");
		return customerService.updateCustomer(updateCustomer);
	}

	@PostMapping("/customer/{customerId}")
	public String deleteCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException {
//		login.validateToken(request,"customer");
		return this.customerService.deactivateCustomerAccountById(customerId);

	}

//	@GetMapping("/allCustomers")
//	public List<Customer> getAllCustomers() {
//		return customerService.getAllCustomers();
//	}

}
