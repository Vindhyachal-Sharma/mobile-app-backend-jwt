package com.mobile.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.service.CartService;
import com.mobile.app.service.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private CustomerService customerService;

	@PostMapping("/mobile/{customerId}/{mobileId}")
	public Cart addMobileToCartByCustomerId(@PathVariable("mobileId") Integer mobileId,
			@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, MobileNotFoundException, CartNotFoundException, UserNotFoundException {

		return cartService.addMobileToCartByCustomerId(mobileId, customerId);
	}

	@PutMapping("/checkout/{customerId}")
	public String makePaymentAndProceed(@RequestBody Payment payment, @PathVariable("customerId") Integer customerId,
			HttpServletRequest request)
			throws CustomerNotFoundException, MobileNotFoundException, CartNotFoundException, UserNotFoundException {

		return cartService.checkout(payment, customerId);
	}
	
	@GetMapping("/{customerId}")
	public Cart getCartById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CartNotFoundException, UserNotFoundException, CustomerNotFoundException {

		return cartService.getCartByCustomerId(customerId);
	}

	@DeleteMapping("/delete/{customerId}")
	public String deleteCartById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CartNotFoundException, CustomerNotFoundException, UserNotFoundException {

		return this.customerService.deleteExistingCartFromCustomerById(customerId);
	}

	@DeleteMapping("/mobile/{customerId}/{mobileId}")
	public Cart deleteMobileFromCartById(@PathVariable("mobileId") Integer mobileId,
			@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws MobileNotFoundException, CartNotFoundException, CustomerNotFoundException, UserNotFoundException {

		return this.cartService.removeMobileFromCart(mobileId, customerId);
	}

}
