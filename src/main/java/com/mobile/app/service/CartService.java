package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;

public interface CartService {

	Cart addMobileToCartByCustomerId(Integer MobileId, Integer custId)
			throws CustomerNotFoundException, MobileNotFoundException, CartNotFoundException;


	Cart removeMobileFromCart(Integer mobileId, Integer id)
			throws CartNotFoundException, CustomerNotFoundException, MobileNotFoundException;


	String deleteCartById(Integer cartId) throws CartNotFoundException, CustomerNotFoundException;

	List<Cart> getAllCarts();

	Cart getCartByCustomerId(Integer customerId) throws CartNotFoundException, CustomerNotFoundException;

	String checkout(Payment payment, Integer cartId) throws CartNotFoundException, CustomerNotFoundException;

}
