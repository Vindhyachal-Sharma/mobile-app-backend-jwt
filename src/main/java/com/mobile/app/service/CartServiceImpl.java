package com.mobile.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.exception.OrderException;
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
	private PaymentService paymentService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository; 

	@Override
	public Cart addMobileToCartByCustomerId(Integer mobileId, Integer customerId)
			throws CustomerException, MobileException, CartException {
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
	public Cart updateCart(Mobile mobile, Integer cartId) throws CartException {
		Optional<Cart> existingCart = cartRepository.findById(cartId);
		if (!existingCart.isPresent()) {
			throw new CartException("Cart Id Not Found");

		}
		Cart updatedCart = existingCart.get();
		updatedCart.setCost(mobile.getMobileCost());
		cartRepository.save(updatedCart);
		return updatedCart;
	}

	@Override
	public Cart removeMobileFromCart(Integer mobileId, Integer customerId)
			throws CartException, CustomerException, MobileException {
		Cart cart = null;
		Customer customer = customerService.getCustomerById(customerId);

		Mobile mob = mobileService.getMobileById(mobileId);

		if (customer.getCart() == null) {
			throw new CartException("Cart not found for Customer");

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

	public Integer updateMobileItemQuantity(Mobile mobile, Integer CustId) throws CustomerException {
		Integer count = 0;

		if (customerService.getCustomerById(CustId).getCart().getMobiles().contains(mobile.getMobileId())) {

			count = customerService.getCustomerById(CustId).getCart().getQuantity();
			count++;
			customerService.getCustomerById(CustId).getCart().setQuantity(count);

		}
		return count;
	}

	public Integer removeMobileItemQuantity(Mobile mobile, Integer CustId) throws CustomerException {
		Integer count = 0;

		if (customerService.getCustomerById(CustId).getCart().getMobiles().contains(mobile.getMobileId())) {

			count = customerService.getCustomerById(CustId).getCart().getQuantity();
			count--;
			customerService.getCustomerById(CustId).getCart().setQuantity(count);

		}
		return count;
	}

	public Double removeMobileAndUpdateTotalCost(Mobile mobile, Integer CustId) throws CustomerException {
		Double totalCost = 0.0;

		totalCost = customerService.getCustomerById(CustId).getCart().getCost();
		totalCost -= mobile.getMobileCost();
		customerService.getCustomerById(CustId).getCart().setCost(totalCost);

		return totalCost;
	}

	public Double addMobileAndUpdateTotalCost(Mobile mobile, Integer CustId) throws CustomerException {
		Double totalCost = 0.0;

		totalCost = customerService.getCustomerById(CustId).getCart().getCost();
		totalCost += mobile.getMobileCost();
		customerService.getCustomerById(CustId).getCart().setCost(totalCost);

		return totalCost;
	}

	@Override
	public Cart getCartById(Integer cartId) throws CartException {
		Optional<Cart> optCart = cartRepository.findById(cartId);
		if (optCart.isEmpty())
			throw new CartException("Cart id not found :" + cartId);

		return optCart.get();

	}

	@Override
	public String deleteCartById(Integer cartId) throws CartException, CustomerException {
		Optional<Cart> optCart = this.cartRepository.findById(cartId);
		if (optCart.isEmpty())
			throw new CartException("cart id does not exists to delete !");
		Cart cart = optCart.get();
		Customer  customer= customerService.getCustomerById(cartId);
		List<Mobile> newList=new ArrayList<>();
		cart.setMobiles(newList);
		cartRepository.save(cart);
		return "Cart Deleted Successfully";

	}

	@Override
	public Cart getCartByCustomerId(Integer customerId) throws CartException, CustomerException {
		Customer customer = customerService.getCustomerById(customerId);

		Cart cart = customer.getCart();
		if (cart == null)
			throw new CartException("Cart  not found :");
		else
			return cart;

	}

	@Override
	public Payment removePaymentFromCartId(Integer cartId) throws OrderException, CartException {
		Cart cart = getCartById(cartId);
		Payment pay = cart.getPayment();
		cart.setPayment(null);
		paymentRepository.delete(pay);
		cartRepository.save(cart);
		return pay;

	}

	@Override
	public String checkout(Payment payment, Integer cartId) throws CartException, CustomerException {

		Payment madePayment = null;
		Cart cart = getCartByCustomerId(cartId);
		Customer customer = customerService.getCustomerById(cartId);
		
		madePayment.setPaymentMode(payment.getPaymentMode());
		madePayment.setPaymentStatus(payment.getPaymentStatus());
		paymentRepository.save(madePayment);
		cart.setPayment(madePayment);
		cartRepository.save(cart);
		
		Orders order = new Orders();
		order.setCost(cart.getCost());
		order.setMobiles(cart.getMobiles());
		order.setQuantity(cart.getMobiles().size());
		order.setOrderDate(LocalDate.now());
//		order.setDispatchDate(LocalDateTime.from(LocalDate.now().toInstant()).plusDays(1));
		order.setDispatchDate(LocalDate.now());
		orderRepository.save(order);
		List<Orders>orderList=new ArrayList<>();
		orderList.add(order);
		customer.setOrders(orderList);
		customerRepository.save(customer);
		List<Mobile> newList=new ArrayList<>();
		cart.setMobiles(newList);
		cartRepository.save(cart);
		
		return "Thanks for the order";
	}

	

}
