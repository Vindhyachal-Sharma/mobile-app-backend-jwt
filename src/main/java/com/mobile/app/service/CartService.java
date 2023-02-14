package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.exception.OrderException;

public interface CartService {

	Cart addMobileToCartByCustomerId(Integer MobileId, Integer custId) throws CustomerException, MobileException, CartException;

	Cart updateCart(Mobile mobile, Integer id) throws CartException;

	Cart removeMobileFromCart(Integer mobileId, Integer id) throws CartException, CustomerException, MobileException;

	Cart getCartById(Integer cartId) throws CartException;

	String deleteCartById(Integer cartId) throws CartException;

	List<Cart> getAllCarts();

	Cart getCartByCustomerId(Integer customerId) throws CartException, CustomerException;

	Payment removePaymenttFromCartId(Integer cartId) throws OrderException, CartException;

}
