package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.OrderException;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Orders addOrder(Orders newOrder) {

		return orderRepository.save(newOrder);
	}

	@Override
	public Orders deleteOrderById(Integer orderId) throws OrderException {

		Optional<Orders> optOrder = this.orderRepository.findById(orderId);
		if (optOrder.isEmpty())
			throw new OrderException("Order id does not exists to delete !");
		Orders order = optOrder.get();
		this.orderRepository.delete(order);
		return order;
	}

	@Override
	public Orders getOrderById(Integer orderId) throws OrderException {
		Optional<Orders> optOrder = orderRepository.findById(orderId);
		if (optOrder.isEmpty())
			throw new OrderException("Order id not found :" + orderId);

		return optOrder.get();
	}

	@Override
	public List<Orders> getAllOrders() {

		return orderRepository.findAll();
	}

	@Override
	public Orders addOrderToCustomer(Orders Order, Integer id) throws CustomerException {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isEmpty()) {
			throw new CustomerException("Customer Id Not Found");

		}
		Customer foundCustomer =customer.get();
		Orders newOrders=addOrder(Order);
		foundCustomer.getOrders().add(newOrders);
		return newOrders;
	}

	@Override
	public String updateOrder(Orders order, Integer id) {
		Optional<Orders> existingOrder = orderRepository.findById(id);
		if (!existingOrder.isPresent()) {
			return "No Order Available to update ";
		}
		Orders updatedOrder = existingOrder.get();
		updatedOrder.setId(id);
		updatedOrder.setOrderDate(order.getOrderDate());
		;
		updatedOrder.setDispatchDate(order.getDispatchDate());
		;
		updatedOrder.setCost(order.getCost());
		updatedOrder.setQuantity(order.getQuantity());
		;
		updatedOrder.setPayment(order.getPayment());
		orderRepository.save(updatedOrder);

		return "Order Successfully Added";
	}

}
