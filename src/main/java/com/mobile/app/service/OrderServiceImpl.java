package com.mobile.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CartException;
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
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	public Orders addOrder(Orders newOrder) {

		return orderRepository.save(newOrder);
	}

	@Override
	public String deleteOrderById(Integer orderId) throws OrderException {

		Optional<Orders> optOrder = this.orderRepository.findById(orderId);
		if (optOrder.isEmpty())
			throw new OrderException("Order id does not exists to delete !");
//		Orders order = optOrder.get();
		this.orderRepository.deleteById(orderId);
		return "Order Deleted Succesfully";
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
	public Orders addOrderToCustomer(Orders Order, Integer customerId) throws CustomerException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new CustomerException("Customer Id Not Found");

		}
		Customer foundCustomer = customer.get();
		Orders newOrders = addOrder(Order);
		foundCustomer.getOrders().add(newOrders);
		orderRepository.save(newOrders);
		customerRepository.save(foundCustomer);
		return newOrders;
	}

	@Override
	public Orders getOrdersFromCart(Integer cartId) throws CartException, CustomerException {
		
		Customer customer = customerService.getCustomerById(cartId);
		Cart cart =cartService.getCartByCustomerId(cartId);
		Orders order = new Orders();
		order.setCost(cart.getCost());
		order.getMobiles().addAll(cart.getMobiles());
		order.setQuantity(cart.getMobiles().size());
		order.setOrderDate(LocalDate.now());
//		order.setDispatchDate(LocalDateTime.from(LocalDate.now().toInstant()).plusDays(1));
		order.setDispatchDate(LocalDate.now());
		orderRepository.save(order);
		List<Orders> orderList = new ArrayList<>();
		orderList.add(order);
		customer.setOrders(orderList);
		customerRepository.save(customer);
		return orderRepository.save(order);
	}

//	@Override
//	public String updateOrder(Orders order) {
//		Optional<Orders> existingOrder = orderRepository.findById(order.getId());
//		if (!existingOrder.isPresent()) {
//			return "No Order Available to update ";
//		}
//		Orders updatedOrder = existingOrder.get();
//		updatedOrder.setId(order.getId());
//		updatedOrder.setOrderDate(order.getOrderDate());
//
//		updatedOrder.setDispatchDate(order.getDispatchDate());
//
//		updatedOrder.setCost(order.getCost());
//		updatedOrder.setQuantity(order.getQuantity());
//
//		return "Order Successfully Updated";
//	}

}
