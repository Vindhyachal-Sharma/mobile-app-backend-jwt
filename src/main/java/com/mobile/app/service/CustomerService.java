package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Customer;
import com.mobile.app.exception.CustomerException;

public interface CustomerService {
	// CRUD
	Customer addCustomer(Customer newCustomer);
	
	Customer updateCustomer(Customer updateCustomer) throws CustomerException;
	
	Customer deleteCustomerById(Integer customerId)throws CustomerException;

	Customer getCustomerById(Integer customerId) throws CustomerException;
	
	List<Customer> getAllCustomers();//for admin 

	

	

}
