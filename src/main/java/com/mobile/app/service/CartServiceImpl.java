package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
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
	public Cart addMobileToCart(Mobile mobile, Integer id) throws CustomerException, MobileException {
		Cart cart = null;
		Customer customer = customerService.getCustomerById(id);

		Mobile mob = mobileService.getMobileById(mobile.getMobileId());

		if (customer.getCart() == null) {
			cart = new Cart();
			cart.getMobilesMap().put(mobile.getMobileId(), mob);

			cart = this.cartRepository.save(cart);
			customer.setCart(cart);

		} else {
			cart = customer.getCart();
			cart.getMobilesMap().put(mobile.getMobileId(), mob);
			cart.setQuantity(updateMobileItemQuantity(mobile, id));
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
	public Cart removeMobileFromCart(Mobile mobile, Integer id) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateMobileItemQuantity(Mobile mobile, Integer CustId) throws CustomerException {
		Integer count = 0;

		if (customerService.getCustomerById(CustId).getCart().getMobilesMap().containsKey(mobile.getMobileId())) {

			count = customerService.getCustomerById(CustId).getCart().getQuantity();
			count++;
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
	public Cart deleteCartById(Integer cartId) throws CartException {
		Optional<Cart> optCart = this.cartRepository.findById(cartId);
		if (optCart.isEmpty())
			throw new CartException("cart id does not exists to delete !");
		Cart cart = optCart.get();
		this.cartRepository.delete(cart);
		return cart;
		
	}


}
