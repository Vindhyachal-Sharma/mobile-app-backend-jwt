package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.OrderException;

public interface OrderService { 
	
	// CRUD
		Orders addOrder(Orders newOrder);
		
		String updateOrder(Orders order);
		
		Orders deleteOrderById(Integer orderId)throws OrderException;

		Orders getOrderById(Integer orderId) throws OrderException;
		
		Orders addOrderToCustomer(Orders Order,Integer id) throws CustomerException;
		
		
		//double calculateTotalCost(List<Mobile> list);
		
		List<Orders> getAllOrders(); 


}
