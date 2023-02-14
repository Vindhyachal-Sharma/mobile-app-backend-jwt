package com.mobile.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.mobile.app.service.CustomerService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CustomerService customerService;
//	--------------------------------------------CartServices---------------------------------------
//	Cart addMobileToCart(Mobile mobile, Integer id) throws CustomerException, MobileException;
//	Cart updateCart(Mobile mobile,Integer id) throws CartException;
//	Cart removeMobileFromCart(Mobile mobile, Integer id) throws CartException;
//	List<Cart> getAllCarts();

	@PostMapping("/cart/{customerId}")
	public Cart addMobileToCartByCustomerId(@Valid @RequestBody Integer mobileId,
			@PathVariable("customerId") Integer customerId) throws CustomerException, MobileException, CartException {

		return cartService.addMobileToCartByCustomerId(mobileId, customerId);
	}

	@PutMapping("/cart/{cartId}")
	public Cart updateCart(@Valid @RequestBody Mobile mobile, @PathVariable("cartId") Integer cartId)
			throws CartException {

		return cartService.updateCart(mobile, cartId);
	}

	@GetMapping("/carts")
	public List<Cart> getAllCarts() {

		return cartService.getAllCarts();
	}

	@GetMapping("/cart/{id}")
	public Cart getCartById(@Valid @PathVariable("id") Integer cartId) throws CartException {

		return cartService.getCartById(cartId);
	}

	@DeleteMapping("/cart/{customerId}")
	public String deleteCartById(@PathVariable("customerId") Integer customerId)
			throws CartException, CustomerException {

		return this.customerService.deleteExistingCartFromCustomerById(customerId);
	}

}
