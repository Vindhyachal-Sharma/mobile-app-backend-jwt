package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;

public interface CartService {

	Cart addMobileToCart(Mobile mobile, Integer id) throws CustomerException, MobileException;

	Cart updateCart(Mobile mobile, Integer id) throws CartException;

	Cart removeMobileFromCart(Mobile mobile, Integer id) throws CartException;

	Cart getCartById(Integer cartId) throws CartException;

	Cart deleteCartById(Integer cartId) throws CartException;

	List<Cart> getAllCarts();

}
