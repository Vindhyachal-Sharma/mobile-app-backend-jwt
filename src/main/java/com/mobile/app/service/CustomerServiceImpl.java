package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.OrderException;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Override
	public Customer addCustomer(Customer newCustomer) {

		return customerRepository.save(newCustomer);
	}

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Cusomer id not found :" + customerId);

		return optCustomer.get();
	}

	@Override
	public Customer updateCustomer(Customer updateCustomer) throws CustomerException {
		Optional<Customer> customerOpt = this.customerRepository.findById(updateCustomer.getId());
		if (customerOpt.isEmpty())
			throw new CustomerException("Customer id does not exist to update.");

		Customer customer = customerOpt.get();
		customer.setName(updateCustomer.getName());
		customer.setMobileNo(updateCustomer.getMobileNo());
		customer.setEmail(updateCustomer.getEmail());

		return this.customerRepository.save(updateCustomer);
	}

	@Override
	public String deleteCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Customer id does not exists to delete !");
		Customer customer = optCustomer.get();
		this.customerRepository.deleteById(customerId);
		return "Id Deleted Successfully";
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	@Override
	public String deleteCartFromCustomerById(Integer customerId, Integer cartId)
			throws CustomerException, CartException {

		Customer customer = customerRepository.findById(customerId).get();

		if (customer == null) {
			throw new CustomerException("Customer Not Found");
		} else {
			if (customer.getCart() != null) {
				customer.setCart(null);
				customerRepository.save(customer);

			} else
				throw new CartException("Requested Cart " + cartId + " Not found");
		}
		return "Cart deleted Succesfully";

	}

	@Override
	public List<Orders> getAllOrdersOfCustomer(Integer customerId) throws CustomerException {
		// return iAppointmentServiceImpl.getAppointmentById(userId);
		Customer customer = getCustomerById(customerId);

		return customer.getOrders();
	}

	@Override
	public String deleteOrdersFromCustomerById(Integer customerId, Integer orderId)
			throws CustomerException, OrderException {
		Orders deletedOrder = null;
		Orders orders = orderRepository.findById(orderId).get();
		Customer customer = customerRepository.findById(customerId).get();

		if (customer == null) {
			throw new CustomerException("Customer Not Found");
		} else {
			if (orders.getMobiles() != null) {
				for (Orders order : customer.getOrders()) {
					if (order.getId() == orderId)
						deletedOrder = order;
				}
				customer.getOrders().remove(deletedOrder);
				orderService.deleteOrderById(orderId);
				customerRepository.save(customer);
			} else
				throw new OrderException("Requested Order" + orderId + "Not found");
		}
		return "Mobile deleted Succesfully";

	}

}
