package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;

public interface CustomerService {
	Customer addCustomer(Customer newCustomer) throws CustomerNotFoundException;

	Customer updateCustomer(Integer customerId, Customer customerData) throws CustomerNotFoundException;

	String deactivateCustomerAccountById(Integer customerId) throws CustomerNotFoundException;

	Customer getCustomerById(Integer customerId) throws CustomerNotFoundException;

	List<Customer> getAllCustomers();

	String deleteExistingCartFromCustomerById(Integer customerId) throws CustomerNotFoundException;

	List<Orders> getAllOrdersOfCustomer(Integer customerId) throws CustomerNotFoundException;

	String cancelOrdersFromCustomerById(Integer customerId, Integer orderId)
			throws CustomerNotFoundException, OrderNotFoundException;

	Customer getCustomerByMobileNo(String mobileNo) throws CustomerNotFoundException;

	Customer getCustomerByUsername(String userName) throws CustomerNotFoundException;

	Customer getCustomerByEmail(String email) throws CustomerNotFoundException;

	String cancelMobileFromOrdersById(Integer customerId, Integer orderId, Integer mobileId)
			throws CustomerNotFoundException, OrderNotFoundException, MobileNotFoundException;

	String activateCustomerAccountById(Integer customerId) throws CustomerNotFoundException;

}
