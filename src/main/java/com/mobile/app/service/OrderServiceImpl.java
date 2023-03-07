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
import com.mobile.app.entity.Orders.OrderStatus;
import com.mobile.app.entity.Payment;
import com.mobile.app.entity.Payment.PaymentStatus;
import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.repository.PaymentRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	public Orders addOrder(Orders newOrder) {

		return orderRepository.save(newOrder);
	}
	
	public String cancelOrderById(Integer orderId) throws OrderNotFoundException {
		Orders order = getOrderById(orderId);
		if (order==null)
			throw new OrderNotFoundException("Order id does not exists to cancel!");
		
		order.setOrderStatus(OrderStatus.CANCELLED);
		order.getPayment().setPaymentStatus(PaymentStatus.REVERSED);
		paymentRepository.save(order.getPayment());
		
		this.orderRepository.save(order);
		return "Order cancelled Successfully amount will be returned to the original payment method";
	}

	@Override
	public Orders getOrderById(Integer orderId) throws OrderNotFoundException {
		
		Optional<Orders> optOrder = orderRepository.findById(orderId);
		if (optOrder.isEmpty())
			throw new OrderNotFoundException("Order id not found :" + orderId);

		return optOrder.get();
	}
	@Override
	public List<Orders> getOrderByCustomerId(Integer customerId)throws CustomerNotFoundException{
		Optional<Customer> customer =customerRepository.findById(customerId);
		
		if (customer.isEmpty())
			throw new CustomerNotFoundException("customer id not found :" + customerId);

		List<Orders>orders= customer.get().getOrders() ;
		
		return orders;
	}

	@Override
	public List<Orders> getAllOrders() {

		return orderRepository.findAll();
	}

	@Override
	public Orders addOrderToCustomer(Orders Order, Integer customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer Id Not Found");

		}
		Customer foundCustomer = customer.get();
		Orders newOrders = addOrder(Order);
		foundCustomer.getOrders().add(newOrders);
		orderRepository.save(newOrders);
		customerRepository.save(foundCustomer);
		return newOrders;
	}

	@Override
	public Orders getOrdersFromCart(Payment payment,Integer cartId) throws CartNotFoundException, CustomerNotFoundException {

		Customer customer = customerService.getCustomerById(cartId);
		Cart cart = cartService.getCartByCustomerId(cartId);

		Orders order = new Orders();
		order.setCost(cart.getCost());
		order.getMobiles().addAll(cart.getMobiles());
		order.setQuantity(cart.getMobiles().size());
		order.setOrderDate(LocalDate.now());
		order.setDispatchDate(LocalDate.now());
		order.setOrderAddress(customer.getAddress());
		order.setOrderStatus(OrderStatus.PLACED);
		orderRepository.save(order);
		
		if(customer.getOrders().isEmpty()||customer.getOrders()==null) {
		
		List<Orders> orderList = new ArrayList<>();
		
		orderList.add(order);
		customer.setOrders(orderList);
		}
		else {
			customer.getOrders().add(order);
		}
		
		customerRepository.save(customer);
		
		
		return orderRepository.save(order);
	}


}
