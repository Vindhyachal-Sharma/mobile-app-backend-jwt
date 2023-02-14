package com.mobile.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Payment;
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

	@PostMapping("/cart/mobile/{customerId}")
	public Cart addMobileToCartByCustomerId(@RequestBody Integer mobileId,
			@PathVariable("customerId") Integer customerId) throws CustomerException, MobileException, CartException {

		return cartService.addMobileToCartByCustomerId(mobileId, customerId);
	}

	@PostMapping("/cart/checkout/{cartId}")
	public String makePaymentAndProceed(@RequestBody Payment payment, @PathVariable("cartId") Integer cartId)
			throws CustomerException, MobileException, CartException {

		return cartService.checkout(payment, cartId);
	}

	@GetMapping("/carts")
	public List<Cart> getAllCarts() {

		return cartService.getAllCarts();
	}

	@GetMapping("/cart/{id}")
	public Cart getCartById(@PathVariable("id") Integer cartId) throws CartException {

		return cartService.getCartById(cartId);
	}

	@DeleteMapping("/cart/delete/{customerId}")
	public String deleteCartById(@PathVariable("customerId") Integer customerId)
			throws CartException, CustomerException {

		return this.customerService.deleteExistingCartFromCustomerById(customerId);
	}

	@DeleteMapping("/cart/mobile/{customerId}")
	public Cart deleteMobileFromCartById(@RequestBody Integer mobileId, @PathVariable("customerId") Integer customerId)
			throws MobileException, CartException, CustomerException {

		return this.cartService.removeMobileFromCart(mobileId, customerId);
	}
}
