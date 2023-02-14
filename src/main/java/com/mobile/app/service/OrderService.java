package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.OrderException;

public interface OrderService { 
	
	// CRUD
		Orders addOrder(Orders newOrder);
		
//		Orders addOrderToCustomer(Integer customerId,Integer OrderId);
		
	//	String updateOrder(Orders order);
		
		String deleteOrderById(Integer orderId)throws OrderException;

		Orders getOrderById(Integer orderId) throws OrderException;
		
		Orders addOrderToCustomer(Orders Order,Integer customerd) throws CustomerException;
		
		
		//double calculateTotalCost(List<Mobile> list);
		
		List<Orders> getAllOrders();

		//Payment removePaymenttByid(Integer orderId) throws OrderException; 


}
