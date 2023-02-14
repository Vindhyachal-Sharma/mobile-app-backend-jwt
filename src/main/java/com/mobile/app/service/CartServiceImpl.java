package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.exception.OrderException;
import com.mobile.app.repository.CartRepository;
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

	@Override
	public Cart addMobileToCartByCustomerId(Integer mobileId, Integer customerId)
			throws CustomerException, MobileException, CartException {
		Customer customer = customerService.getCustomerById(customerId);

		Cart cart = customer.getCart();

		Mobile mob = mobileService.getMobileById(mobileId);

		cart = customer.getCart();
		cart.getMobiles().add(mob);
		cart.setQuantity(updateMobileItemQuantity(mob, customerId));
		cart.setCost(addMobileAndUpdateTotalCost(mob, customerId));
		customer.setCart(cart);

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
			cart.setQuantity(removeMobileItemQuantity(mob, customerId));
			cart.setCost(removeMobileAndUpdateTotalCost(mob, customerId));
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

		if (customerService.getCustomerById(CustId).getCart().getMobiles().contains(mobile.getMobileId())) {

			totalCost = customerService.getCustomerById(CustId).getCart().getCost();
			totalCost -= mobile.getMobileCost();
			customerService.getCustomerById(CustId).getCart().setCost(totalCost);

		}
		return totalCost;
	}

	public Double addMobileAndUpdateTotalCost(Mobile mobile, Integer CustId) throws CustomerException {
		Double totalCost = 0.0;

		if (customerService.getCustomerById(CustId).getCart().getMobiles().contains(mobile.getMobileId())) {

			totalCost = customerService.getCustomerById(CustId).getCart().getCost();
			totalCost += mobile.getMobileCost();
			customerService.getCustomerById(CustId).getCart().setCost(totalCost);

		}
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
	public String deleteCartById(Integer cartId) throws CartException {
		Optional<Cart> optCart = this.cartRepository.findById(cartId);
		if (optCart.isEmpty())
			throw new CartException("cart id does not exists to delete !");
		Cart cart = optCart.get();
		this.cartRepository.deleteById(cartId);
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

	public Payment removePaymenttFromCartId(Integer cartId) throws OrderException, CartException {
		Cart cart = getCartById(cartId);
		Payment pay = cart.getPayment();
		cart.setPayment(null);
		paymentRepository.delete(pay);
		cartRepository.save(cart);
		return pay;

	}
}
