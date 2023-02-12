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
import com.mobile.app.exception.MobileException;
import com.mobile.app.repository.CartRepository;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MobileService mobileService;

	@Override
	public Cart addMobileToCartByCustomerId(Mobile mobile, Integer customerId)
			throws CustomerException, MobileException {
		Cart cart = null;
		Customer customer = customerService.getCustomerById(customerId);

		Mobile mob = mobileService.getMobileById(mobile.getMobileId());

		if (customer.getCart() == null) {
			cart = new Cart();
			cart.getMobiles().add(mobile);

			cart = this.cartRepository.save(cart);
			customer.setCart(cart);

		} else {
			cart = customer.getCart();
			cart.getMobiles().add(mobile);
			cart.setQuantity(updateMobileItemQuantity(mobile, customerId));
		}

		return this.cartRepository.save(cart);

	}

	@Override
	public List<Cart> getAllCarts() {

		// TODO Auto-generated method stub
		return this.cartRepository.findAll();
	}

	@Override
	public Cart updateCart(Mobile mobile, Integer id) throws CartException {
		Optional<Cart> existingCart = cartRepository.findById(id);
		if (!existingCart.isPresent()) {
			throw new CartException("Cart Id Not Found");

		}
		Cart updatedCart = existingCart.get();
		updatedCart.setCost(mobile.getMobileCost());
		return updatedCart;
	}

//	@Override
//	public Cart removeMobileFromCart(Mobile mobile,Integer id) throws CustomerException {
//		
//		List mobilesList=customerService.getCustomerById(id).getCart().getMobiles();
//		boolean isPresent=mobilesList.contains(mobile);
//		if(isPresent) {
//			if(Collections.frequency(mobilesList,mobile)>1)
//				mobilesList.remove(mobile);
//			
//		}
//		return this.cartRepository.save(cart)
//	}

	@Override
	public Cart removeMobileFromCart(Mobile mobile, Integer customerId)
			throws CartException, CustomerException, MobileException {
		Cart cart = null;
		Customer customer = customerService.getCustomerById(customerId);

		Mobile mob = mobileService.getMobileById(mobile.getMobileId());

		if (customer.getCart() == null) {
			throw new CartException("Cart not found for Customer");

		} else {
			cart = customer.getCart();
			cart.getMobiles().remove(mobile);
			cart.setQuantity(removeMobileItemQuantity(mobile, customerId));
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
		return "Cart Deleted Successfully";

	}
	
	
	
	

}
