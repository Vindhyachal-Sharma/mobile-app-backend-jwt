package com.mobile.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobile.app.exception.CartNotFoundException;
import com.mobile.app.service.CartService;
@SpringBootTest
class CartServiceImplTest {



	@Autowired
	private CartService cartservice;

	
//	@Test
//	void addCartToCustomerByIdExceptionTest() {
//
//		assertThrows(CartNotFoundException.class, () -> cartservice.addCartToCustomerById(null, 4));
//	}

	@Test
	void getCartByIdExceptionTest() {

		assertThrows(CartNotFoundException.class, () -> cartservice.getCartById(500));
	}
//	@Test
//	void getCartByCustomerIdExceptionTest() {
//
//		assertThrows(CartNotFoundException.class, () -> customerservice.getCartByCustomerId(500));
//	}

	@Test
	void getCartByIdExceptionMessageTest() {

		String msg = null;
		try {
			cartservice.getCartById(500);
		} catch (CartNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			msg = e.getMessage();
		}

		assertEquals("Cart id not found :500", msg);
	}

	@Test
	void updateCartNotFoundExceptionTest() {

		assertThrows(CartNotFoundException.class, () -> cartservice.updateCart(null, 1));
	}

	

	@Test
	void deleteCartNotFoundExceptionTest() {

		assertThrows(CartNotFoundException.class, () -> cartservice.deleteCartById(2));
	}

}
