package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.repository.CartRepository;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderService orderService;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerNotFoundException{
		Customer optCustomer = this.customerRepository.findByEmail(customer.getEmail());
		Customer optMobile =this.customerRepository.findByMobileNo(customer.getMobileNo());
		
		if(optCustomer!=null) {
			throw new CustomerNotFoundException("Email already registered");
		}
		if(optMobile!=null) {
			throw new CustomerNotFoundException("Mobile number already registered");
		}
		Customer newCustomer = customerRepository.save(customer);
		customer.setStatus("Active");
		Cart cart = new Cart(customer.getUserId(), 0, 0.0);
		cartRepository.save(cart);
		customer.setCart(cart);
		customer.setRole("customer");
		customerRepository.save(customer);
		return newCustomer;
	}

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerNotFoundException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerNotFoundException("Cusomer id not found :" + customerId);

		return optCustomer.get();
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customerOpt = this.customerRepository.findById(customer.getUserId());
		if (customerOpt.isEmpty())
			throw new CustomerNotFoundException("Customer id does not exist to update.");

		Customer updateCustomer = customerOpt.get();
		updateCustomer.setName(customer.getName());
		updateCustomer.setMobileNo(customer.getMobileNo());
		updateCustomer.setEmail(customer.getEmail());

		return this.customerRepository.save(updateCustomer);
	}

	@Override
	public String deactivateCustomerAccountById(Integer customerId) throws CustomerNotFoundException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerNotFoundException("Customer id does not exists to deactivate account !");
		Customer customer = optCustomer.get();
		customer.setStatus("Inactive");
		this.customerRepository.save(customer);
		return "Id Deactivated Successfully will get activated when logged in again";
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	@Override
	public String deleteExistingCartFromCustomerById(Integer customerId) throws CustomerNotFoundException {

		Customer customer = customerRepository.findById(customerId).get();

		if (customer == null) {
			throw new CustomerNotFoundException("Customer Not Found");
		} else {
			customer.getCart().setCost(0.0);
			customer.getCart().setQuantity(0);
			customer.getCart().getMobiles().clear();
			customerRepository.save(customer);

		}

		return "Cart deleted Succesfully";

	}

	@Override
	public List<Orders> getAllOrdersOfCustomer(Integer customerId) throws CustomerNotFoundException {
		Customer customer = getCustomerById(customerId);

		return customer.getOrders();
	}

	@Override
	public String cancelOrdersFromCustomerById(Integer customerId, Integer orderId)
			throws CustomerNotFoundException, OrderNotFoundException {
		Orders deletedOrder = null;
		Orders orders = orderRepository.findById(orderId).get();
		Customer customer = getCustomerById(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer Not Found");
		} else {
			if (!(orders.getMobiles().isEmpty() || orders.getMobiles() == null)){
				for (Orders order : customer.getOrders()) {
					if (order.getId() == orderId)
						deletedOrder = order;
				}
				customer.getOrders().remove(deletedOrder);
				orderService.deleteOrderById(orderId);
				
				customerRepository.save(customer);
			} else
				throw new OrderNotFoundException("Requested Order" + orderId + "Not found");
		}
		return "Order Cancelled Succesfully payement will be sent back to the original payment method within few days";

	}

	

}
