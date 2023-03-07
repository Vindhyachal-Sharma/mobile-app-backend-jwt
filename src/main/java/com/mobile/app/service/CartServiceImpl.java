package com.mobile.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Orders.OrderStatus;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.repository.CartRepository;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.repository.PaymentRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MobileService mobileService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderService orderService;
	

	@Override
	public Cart addMobileToCartByCustomerId(Integer mobileId, Integer customerId)
			throws CustomerNotFoundException, MobileNotFoundException, CartNotFoundException {
		Customer customer = customerService.getCustomerById(customerId);

		Cart cart = customer.getCart();

		Mobile mob = mobileService.getMobileById(mobileId);

		cart.getMobiles().add(mob);
		cart.setQuantity(cart.getMobiles().size());
		cart.setCost(addMobileAndUpdateTotalCost(mob, customerId));
		customer.setCart(cart);
		customerRepository.save(customer);
		return this.cartRepository.save(cart);

	}

	@Override
	public List<Cart> getAllCarts() {

		return this.cartRepository.findAll();
	}

	@Override
	public Cart removeMobileFromCart(Integer mobileId, Integer customerId)
			throws CartNotFoundException, CustomerNotFoundException, MobileNotFoundException {
		Cart cart = null;
		Customer customer = customerService.getCustomerById(customerId);

		Mobile mob = mobileService.getMobileById(mobileId);

		if (customer.getCart() == null) {
			throw new CartNotFoundException("Cart not found for Customer");

		} else {
			cart = customer.getCart();
			cart.getMobiles().remove(mob);
			cart.setQuantity(cart.getMobiles().size());
			cart.setCost(removeMobileAndUpdateTotalCost(mob, customerId));
			customer.setCart(cart);
			customerRepository.save(customer);
			cartRepository.save(cart);
		}

		return this.cartRepository.save(cart);

	}

	public Integer updateMobileItemQuantity(Mobile mobile, Integer CustId) throws CustomerNotFoundException {
		Integer count = 0;

		if (customerService.getCustomerById(CustId).getCart().getMobiles().contains(mobile.getMobileId())) {

			count = customerService.getCustomerById(CustId).getCart().getQuantity();
			count++;
			customerService.getCustomerById(CustId).getCart().setQuantity(count);

		}
		return count;
	}

	public Integer removeMobileItemQuantity(Mobile mobile, Integer CustId) throws CustomerNotFoundException {
		Integer count = 0;

		if (customerService.getCustomerById(CustId).getCart().getMobiles().contains(mobile.getMobileId())) {

			count = customerService.getCustomerById(CustId).getCart().getQuantity();
			count--;
			customerService.getCustomerById(CustId).getCart().setQuantity(count);

		}
		return count;
	}

	public Double removeMobileAndUpdateTotalCost(Mobile mobile, Integer CustId) throws CustomerNotFoundException {
		Double totalCost = 0.0;

		totalCost = customerService.getCustomerById(CustId).getCart().getCost();
		totalCost -= mobile.getMobileCost();
		customerService.getCustomerById(CustId).getCart().setCost(totalCost);

		return totalCost;
	}

	public Double addMobileAndUpdateTotalCost(Mobile mobile, Integer CustId) throws CustomerNotFoundException {
		Double totalCost = 0.0;

		totalCost = customerService.getCustomerById(CustId).getCart().getCost();
		totalCost += mobile.getMobileCost();
		customerService.getCustomerById(CustId).getCart().setCost(totalCost);

		return totalCost;
	}

	@Override
	public String deleteCartById(Integer cartId) throws CartNotFoundException, CustomerNotFoundException {
		Optional<Cart> optCart = this.cartRepository.findById(cartId);
		if (optCart.isEmpty())
			throw new CartNotFoundException("cart id does not exists to delete !");
		Cart cart = optCart.get();
		Customer customer = customerService.getCustomerById(cartId);
		cart.getMobiles().clear();
		cart.setQuantity(0);
		cart.setCost(0.0);
		cartRepository.save(cart);
		return "Cart Deleted Successfully";

	}

	@Override
	public Cart getCartByCustomerId(Integer customerId) throws CartNotFoundException, CustomerNotFoundException {
		Customer customer = customerService.getCustomerById(customerId);

		Cart cart = customer.getCart();
		if (cart == null)
			throw new CartNotFoundException("Cart  not found :");
		else
			return cart;

	}

	@Override
	public String checkout(Payment payment, Integer cartId) throws CartNotFoundException, CustomerNotFoundException {

		Payment madePayment = new Payment();
		Cart cart = getCartByCustomerId(cartId);
		Customer customer = customerService.getCustomerById(cartId);
		if (cart.getQuantity() == 0) {
			throw new CartNotFoundException("Please Add Some Items Before Checkout");
		} else {
			madePayment.setPaymentMode(payment.getPaymentMode());
			madePayment.setPaymentStatus(payment.getPaymentStatus());
			paymentRepository.save(madePayment);

			Orders order = new Orders();
			order.setCost(cart.getCost());		
			order.setQuantity(cart.getMobiles().size());
			order.setOrderDate(LocalDate.now());
			order.setDispatchDate(LocalDate.now());
			order.setOrderAddress(customer.getAddress());
			order.setOrderStatus(OrderStatus.PLACED);
			order.setPayment(madePayment);
			order.getMobiles().addAll(cart.getMobiles());	
			customer.getOrders().add(order);
			orderService.addOrder(order);

			cart.getMobiles().clear();
			cart.setQuantity(0);
			cart.setCost(0.0);
		cartRepository.save(cart);
			customerRepository.save(customer);

			return "Thanks for the order";
		}
	}
	

}
