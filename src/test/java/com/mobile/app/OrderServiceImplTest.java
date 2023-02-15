package com.mobile.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Orders;
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.repository.CustomerRepository;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.service.OrderServiceImpl;
@SpringBootTest
class OrderServiceImplTest {


	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@MockBean
	private OrderRepository repository;
	@MockBean
	private CustomerRepository customerRepository;

	Payment pay = new Payment(null, null, null);
	Cart cart = new Cart(null, null, null, null,null);
	LocalDate orderDate = LocalDate.parse("2023-02-10");
	LocalDate dispatchDate = LocalDate.parse("2023-09-29");
	Orders orders = new Orders(100, orderDate, dispatchDate, 989898.0, 2, null);

	@Test
	void addOrdersTest() throws Exception{
		
		when(repository.save(orders)).thenReturn(orders);
		assertEquals(orders, orderServiceImpl.addOrder(orders));
		
		//assertNotNull(OrderServiceImpl.addOrders(Orders));
	}

	@Test
	void addOrdersToCustomerTest() throws Exception {
		List<Orders> app = new ArrayList<>();
		Customer cust = new Customer(100, "Vishal", "vishal@email.com", "9898989898", cart, app);
		cust.getOrders().add(orders);
		when(customerRepository.findById(100)).thenReturn(Optional.of(cust));
		when(repository.save(orders)).thenReturn(orders);
		Orders result = orderServiceImpl.addOrderToCustomer(orders, 100);
		assertNotNull(result);
		assertEquals(orders, result);
		assertTrue(cust.getOrders().contains(orders));
	}

//	@Test
//	void addOrdersToCustomerExceptionTest() throws Exception {
//		String msg = null;
//		when(customerRepository.findById(300)).thenReturn(Optional.empty());
//		try {
//			orderServiceImpl.addOrderToCustomer(orders, 300);
//		} catch (Exception e) {
//			msg = e.getMessage();
//		}
//		assertEquals("No customer with this id " + 300 + " found", msg);
//	}

	@Test
	void getOrdersByIdTest() throws Exception {
		Integer id = 100;

		when(repository.findById(id)).thenReturn(Optional.of(orders));

		assertEquals(orders, orderServiceImpl.getOrderById(id));
	}

	@Test
	void getOrdersByIdExceptionMessageTest() {

		String msg = null;
		try {
			orderServiceImpl.getOrderById((Integer) 100);
		} catch (OrderNotFoundException e) {
			msg = e.getMessage();
		}

		assertEquals("No Orders with this id " + 100 + " found", msg);
	}

	@Test
	void updateOrdersTest() throws OrderNotFoundException {
		Integer id = 100;
		LocalDate date = LocalDate.parse("2023-02-10");
		Orders updateOrders = new Orders(100, date, date, 989898.0, 2, null);

		when(repository.findById(id)).thenReturn(Optional.of(orders));
		when(repository.save(updateOrders)).thenReturn(updateOrders);
//		assertEquals(updateOrders, orderServiceImpl.updateOrder(updateOrders));

	}

	@Test
	void updateOrdersDateTest() throws OrderNotFoundException {
		Integer id = 100;
		Payment pay = new Payment(null, null, null);
		LocalDate date = LocalDate.parse("2023-02-11");
		Orders updateOrders = new Orders(100, date, date, 989898.0, 2, null);

		when(repository.findById(id)).thenReturn(Optional.of(orders));
		when(repository.save(updateOrders)).thenReturn(updateOrders);
//		assertEquals(updateOrders, orderServiceImpl.updateOrder(updateOrders));
		// OrderServiceImpl.updateOrders(Integer id, Orders Orderss);
	}

	@Test
	void getAllOrderss() {
		assertNotNull(orderServiceImpl.getAllOrders());
	}


}
