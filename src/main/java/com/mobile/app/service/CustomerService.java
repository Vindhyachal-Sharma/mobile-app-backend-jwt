package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.exception.OrderException;

public interface CustomerService {
	// CRUD
	Customer addCustomer(Customer newCustomer);
	
	Customer updateCustomer(Customer updateCustomer) throws CustomerException;
	
	String deleteCustomerById(Integer customerId)throws CustomerException;

	Customer getCustomerById(Integer customerId) throws CustomerException;
	
	List<Customer> getAllCustomers();//for admin
	


	String deleteExistingCartFromCustomerById(Integer customerId)throws CustomerException;

	List<Orders> getAllOrdersOfCustomer(Integer customerId) throws CustomerException;

	String cancelOrdersFromCustomerById(Integer customerId, Integer orderId) throws CustomerException, OrderException;
	
	

}
