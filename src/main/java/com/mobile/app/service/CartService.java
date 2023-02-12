package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;

public interface CartService {

	Cart addMobileToCartByCustomerId(Mobile mobile, Integer custId) throws CustomerException, MobileException;

	Cart updateCart(Mobile mobile, Integer id) throws CartException;

	Cart removeMobileFromCart(Mobile mobile, Integer id) throws CartException, CustomerException, MobileException;

	Cart getCartById(Integer cartId) throws CartException;

	String deleteCartById(Integer cartId) throws CartException;

	List<Cart> getAllCarts();

}
