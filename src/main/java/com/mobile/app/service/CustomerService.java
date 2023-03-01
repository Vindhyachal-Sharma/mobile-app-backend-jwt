package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;

public interface CustomerService {
	// CRUD
	Customer addCustomer(Customer newCustomer) throws CustomerNotFoundException;

	Customer updateCustomer(Customer updateCustomer) throws CustomerNotFoundException;

	String deactivateCustomerAccountById(Integer customerId) throws CustomerNotFoundException;

	Customer getCustomerById(Integer customerId) throws CustomerNotFoundException;

	List<Customer> getAllCustomers();

	String deleteExistingCartFromCustomerById(Integer customerId) throws CustomerNotFoundException;

	List<Orders> getAllOrdersOfCustomer(Integer customerId) throws CustomerNotFoundException;

	String cancelOrdersFromCustomerById(Integer customerId, Integer orderId)
			throws CustomerNotFoundException, OrderNotFoundException;

}
