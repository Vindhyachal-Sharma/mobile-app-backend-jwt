package com.mobile.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
//	--------------------------------------------CartServices---------------------------------------
//	Cart addMobileToCart(Mobile mobile, Integer id) throws CustomerException, MobileException;
//	Cart updateCart(Mobile mobile,Integer id) throws CartException;
//	Cart removeMobileFromCart(Mobile mobile, Integer id) throws CartException;
//	List<Cart> getAllCarts();

	@PostMapping("/cart/{id}")
	public Cart registerCart(@RequestBody Mobile mobile, @PathVariable("id") Integer id)
			throws CustomerException, MobileException {

		return cartService.addMobileToCart(mobile, id);
	}

	@PutMapping("/cart/{id}")
	public Cart updateCart(@RequestBody Mobile mobile, @PathVariable("id") Integer id) throws CartException {

		return cartService.updateCart(mobile,id);
	}

	@GetMapping("/carts")
	public List<Cart> getAllCarts(){

		return cartService.getAllCarts();
	}

	@GetMapping("/cart/{id}")
	public Cart getCartById(@PathVariable("id") Integer cartId) throws CartException {

		return cartService.getCartById(cartId);
	}

	@DeleteMapping("/card/{id}")
	public Cart deleteCartById(@PathVariable("id") Integer cartId) throws CartException {

		return this.cartService.deleteCartById(cartId);
	}

}
