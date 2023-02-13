package com.mobile.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CartException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.OrderException;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.service.CartService;
import com.mobile.app.service.CustomerServiceImpl;
import com.mobile.app.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private CartService cartService;

	@Mock
	private OrderService orderService;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer customer;
	private Orders orders;

	@BeforeEach
	void setup() {
		customer = new Customer();
		customer.setId(1);
		customer.setName("John Doe");
		customer.setMobileNo(1234567890);
		customer.setEmail("johndoe@email.com");

		orders = new Orders();
		orders.setId(1);
		orders.setCost(100.0);

	}

	@Test
	void testAddCustomer() {
		when(customerRepository.save(customer)).thenReturn(customer);

		Customer addedCustomer = customerService.addCustomer(customer);

		assertThat(addedCustomer).isEqualTo(customer);
	}

	@Test
	void testGetCustomerById() throws CustomerException {
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

		Customer foundCustomer = customerService.getCustomerById(customer.getId());

		assertThat(foundCustomer).isEqualTo(customer);
	}

	@Test
	void testGetCustomerById_CustomerNotFound() {
		Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

		assertThrows(CustomerException.class, () -> customerService.getCustomerById(customer.getId()));
	}

	@Test
	public void testGetCustomerById_Success() throws CustomerException {
		Integer customerId = 1;
		Customer expectedCustomer = new Customer();
		expectedCustomer.setId(customerId);
		expectedCustomer.setName("John Doe");
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

		Customer actualCustomer = customerService.getCustomerById(customerId);

		assertEquals(expectedCustomer, actualCustomer);
	}

	@Test
	public void testUpdateCustomer_Success() throws CustomerException {
		Integer customerId = 1;
		Customer expectedCustomer = new Customer();
		expectedCustomer.setId(customerId);
		expectedCustomer.setName("John Doe");
		expectedCustomer.setMobileNo(1234567890);
		expectedCustomer.setEmail("johndoe@example.com");
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));
		when(customerRepository.save(expectedCustomer)).thenReturn(expectedCustomer);

		Customer updateCustomer = new Customer();
		updateCustomer.setId(customerId);
		updateCustomer.setName("Jane Doe");
		updateCustomer.setMobileNo(987654321);
		updateCustomer.setEmail("janedoe@example.com");

		Customer actualCustomer = customerService.updateCustomer(updateCustomer);

		assertEquals(expectedCustomer, actualCustomer);
		assertEquals(updateCustomer.getName(), expectedCustomer.getName());
		assertEquals(updateCustomer.getMobileNo(), expectedCustomer.getMobileNo());
		assertEquals(updateCustomer.getEmail(), expectedCustomer.getEmail());
	}

//	@Test(expected = CustomerException.class)
//	public void testUpdateCustomer_CustomerNotFound() throws CustomerException {
//		Integer customerId = 2;
//		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
//
//		Customer updateCustomer = new Customer();
//		updateCustomer.setId(customerId);
//
//		customerService.updateCustomer(updateCustomer);
//	}
	@Test
	public void testDeleteCustomerById_Success() throws CustomerException {
		Integer customerId = 1;
		Customer customer = new Customer();
		customer.setId(customerId);
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		String expectedResult = "Id Deleted Successfully";
		String actualResult = customerService.deleteCustomerById(customerId);
		verify(customerRepository).deleteById(customerId);
		assertEquals(expectedResult, actualResult);
	}

//	@Test(expected = CustomerException.class)
//	public void testDeleteCustomerById_CustomerNotFound() throws CustomerException {
//		Integer customerId = 2;
//		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
//		customerService.deleteCustomerById(customerId);
//	}

	@Test
	public void testGetAllCustomers_Success() {
		List<Customer> expectedCustomers = new ArrayList<>();
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("John Doe");
		customer1.setMobileNo(1234567890);
		customer1.setEmail("johndoe@example.com");
		expectedCustomers.add(customer1);
		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setName("Jane Doe");
		customer2.setMobileNo(987654321);
		customer2.setEmail("janedoe@example.com");
		expectedCustomers.add(customer2);
		when(customerRepository.findAll()).thenReturn(expectedCustomers);
		List<Customer> actualCustomers = customerService.getAllCustomers();
		assertEquals(expectedCustomers, actualCustomers);
	}

	@Test
	public void testDeleteCartFromCustomerByIdSuccess() throws CustomerException, CartException {
		Integer customerId = 1;
		Integer cartId = 1;

		Customer mockCustomer = new Customer();
		mockCustomer.setId(customerId);

		Cart mockCart = new Cart();
		mockCart.setId(cartId);

		when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
		when(cartService.deleteCartById(cartId)).thenReturn("Cart deleted Successfully");

		String result = customerService.deleteCartFromCustomerById(customerId, cartId);

		assertEquals("Cart deleted Successfully", result);
		verify(customerRepository, times(1)).findById(customerId);
		verify(cartService, times(1)).deleteCartById(cartId);
	}

//	@Test(expected = CustomerException.class)
//	public void testDeleteCartFromCustomerByIdCustomerNotFound() throws CustomerException, CartException {
//		Integer customerId = 1;
//		Integer cartId = 1;
//
//		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
//
//		customerService.deleteCartFromCustomerById(customerId, cartId);
//	}
//
//	@Test(expected = CartException.class)
//	public void testDeleteCartFromCustomerByIdCartNotFound() throws CustomerException, CartException {
//		Integer customerId = 1;
//		Integer cartId = 1;
//
//		Customer mockCustomer = new Customer();
//		mockCustomer.setId(customerId);
//
//		when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
//		when(cartService.deleteCartById(cartId)).thenThrow(new CartException("Requested Cart Not found"));
//
//		customerService.deleteCartFromCustomerById(customerId, cartId);
//	}

	@Test
	public void testGetAllOrdersOfCustomer() throws CustomerException {
		Integer customerId = 1;
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setName("John Doe");

		List<Orders> orders = new ArrayList<>();
		Orders order = new Orders();
		order.setId(1);
//		order.setOrderDate("2022-01-01");
		order.setCost(100.0);
		orders.add(order);
		customer.setOrders(orders);

		when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(customer));
		when(orderRepository.findById(customerId).get()).thenReturn((Orders) orders);

		List<Orders> result = customerService.getAllOrdersOfCustomer(customerId);

		assertEquals(orders, result);
	}

	@Test
	public void testDeleteOrdersFromCustomerById() throws CustomerException, OrderException {
		Integer customerId = 1;
		Integer orderId = 2;
		Customer customer = new Customer();
		Orders order = new Orders();
		order.setId(orderId);
		List<Orders> orders = new ArrayList<>();
		orders.add(order);
		customer.setOrders(orders);

		Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
		Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

		String result = customerService.deleteOrdersFromCustomerById(customerId, orderId);

		assertEquals("Mobile deleted Succesfully", result);
		Mockito.verify(orderService, Mockito.times(1)).deleteOrderById(orderId);
		Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
		assertEquals(0, customer.getOrders().size());
	}

}