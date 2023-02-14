package com.mobile.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.service.CustomerService;

@SpringBootTest
class MobileApplicationTests {

	//--------------------------customerServiceTestCases
//  Customer addCustomer(Customer newCustomer);
//	
//	Customer updateCustomer(Customer updateCustomer);
//	
//	Customer deleteCustomerById(Integer customerId)throws CustomerException;
//
//	Customer getCustomerById(Integer customerId) throws CustomerException;
//	
//	List<Customer> getAllCustomers();//for admin 

	
	@Autowired
	private CustomerService customerService;

//	@Test
//	void addCustomerTest() {
//		assertNotNull(customerService.addCustomer(new Customer(100, "test name 100", "test100@gmail.com", "test@100")));
//	}
	@Test
	void getCustomerByIdTest() throws CustomerNotFoundException {
		assertNotNull(customerService.getCustomerById(100));
	}
	@Test
	void getCustomerByIdExceptionTest() {
		
		assertThrows(CustomerNotFoundException.class,()->customerService.getCustomerById(500));
	}
	@Test
	void getCustomerByIdExceptionMessageTest() {
		
		String msg=null;
		try {
			customerService.getCustomerById(500);
		} catch (CustomerNotFoundException e) {
			msg=e.getMessage();
		}
		
		assertEquals("Cusomer id not found :500", msg);
	}
		

}
