package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Mobile.MobileOrderStatus;
import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Orders.OrderStatus;
import com.mobile.app.entity.Payment.PaymentStatus;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.repository.CartRepository;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.repository.PaymentRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private PaymentRepository paymenttRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MobileService mobileService;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerNotFoundException{
		Customer email = this.customerRepository.findByEmail(customer.getEmail());
		Customer mobile =this.customerRepository.findByMobileNo(customer.getMobileNo());
		Customer userName =this.customerRepository.findByUserName(customer.getUserName());


		
		if(userName!=null) {
			throw new CustomerNotFoundException("Email already registered");
		}
		
		if(email!=null) {
			throw new CustomerNotFoundException("Email already registered");
		}
		if(mobile!=null) {
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
	public Customer getCustomerByMobileNo(String mobileNo) throws CustomerNotFoundException {

		Customer optCustomer = customerRepository.findByMobileNo(mobileNo);
		if (optCustomer==null)
			throw new CustomerNotFoundException("Cusomer  not found with mobile number:" + mobileNo);

		return optCustomer;
	}
	@Override
	public Customer getCustomerByUsername(String userName) throws CustomerNotFoundException {

		Customer optCustomer = customerRepository.findByUserName(userName);
		if (optCustomer==null)
			throw new CustomerNotFoundException("Cusomer not found with Username:" + userName);

		return optCustomer;
	}
	@Override
	public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {

		Customer optCustomer = customerRepository.findByEmail(email);
		if (optCustomer==null)
			throw new CustomerNotFoundException("Cusomer not found with emial:" + email);

		return optCustomer;
	}
	@Override
	public Customer updateCustomer(Integer customerId,Customer customerData) throws CustomerNotFoundException {
		Customer customer = getCustomerById(customerId);
		if (customer==null)
			throw new CustomerNotFoundException("Customer id does not exist to update.");

		Customer updateCustomer = customer;
		updateCustomer.setName(customerData.getName());
		updateCustomer.setAddress(customerData.getAddress());
		return this.customerRepository.save(updateCustomer);
	}


	@Override
	public String deactivateCustomerAccountById(Integer customerId) throws CustomerNotFoundException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerNotFoundException("Customer id does not exists to deactivate account !");
		Customer customer = optCustomer.get();
		customer.setStatus("InActive");
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
		
		Orders cancelledOrder = orderService.getOrderById(orderId);
		Customer customer = getCustomerById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer Not Found");
		} else {
		if (!(cancelledOrder.getMobiles().isEmpty() || cancelledOrder.getMobiles() == null)){
				for (Orders order : customer.getOrders()) {
					for(Mobile mobile: cancelledOrder.getMobiles()) {
						
						mobile.setOrderStatusOfMobile(MobileOrderStatus.Cancelled);
					}
						
				}
				cancelledOrder.setOrderStatus(OrderStatus.CANCELLED);
				cancelledOrder.getPayment().setPaymentStatus(PaymentStatus.REVERSED);
				paymenttRepository.save(cancelledOrder.getPayment());
				orderRepository.save(cancelledOrder);
				
				customerRepository.save(customer);
			} else
				throw new OrderNotFoundException("Requested Order" + orderId + "Not found");
		}
		return "Order Cancelled Succesfully payment will be sent back to the original payment method within few days";

	}
	
	@Override
	public String cancelMobileFromOrdersById(Integer customerId, Integer orderId,Integer mobileId)
			throws CustomerNotFoundException, OrderNotFoundException, MobileNotFoundException {
		Orders order = orderService.getOrderById(orderId);
		
		Mobile cancelledMobile = mobileService.getMobileById(mobileId);
		
		Customer customer = getCustomerById(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		if(order==null) {
			throw new OrderNotFoundException("Requested Order " + orderId + " Not found");	
		}
		if(cancelledMobile==null) {
			throw new MobileNotFoundException("Requested Mobile " + mobileId + " Not found");	
		}
		
			order.setOrderStatus(OrderStatus.PARTIALLY_CANCELLED);
		   cancelledMobile.setOrderStatusOfMobile(MobileOrderStatus.Cancelled);
				orderRepository.save(order);
				
				customerRepository.save(customer);
			
		return "Mobile Cancelled Succesfully payment will be sent back to the original payment method within few days";

	}
}