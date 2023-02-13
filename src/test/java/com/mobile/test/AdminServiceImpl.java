package com.mobile.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;
import com.mobile.app.repository.OrderRepository;
import com.mobile.app.service.CategoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
	public class AdminServiceImpl {

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private MobileRepository mobileRepository;
	@Mock
	private OrderRepository orderRepository;
	private Category category;

	

	@Test
	public void testAddCategorySuccess() throws CategoryException {
	when(categoryRepository.save(category)).thenReturn(category);
	Category result = categoryServiceImpl.addCategory(category);
	assertEquals("Smartphones", result.getName());
	}
	@Test
	public void testUpdateCategoryDetailsSuccess() throws CategoryException {
	when(categoryRepository.save(category)).thenReturn(category);
	String result = categoryServiceImpl.updateCategory(category);
	assertEquals("Category updated successfully", result);
	}
//	@Test
//	public void testAddMobileSuccess() throws CategoryException {
//		
//	when(categoryRepository.findById(1)).thenReturn(java.util.Optional.of(category));
//	when(mobileRepository.save(mobile)).thenReturn(mobile);
//	Mobile result = mobileService.addMobile(mobile, 1);
//	assertEquals(mobile, result);
//	}
//	@Test
//	public void testAddOrderSuccess() {
//	when(orderRepository.save(order)).thenReturn(order);
//	Orders result = orderServiceImpl.addOrder(order, 1);
//	assertEquals(order, result);
//	}
//	@Test
//	public void testUpdateAdminDetailsSuccess() {
//	when(adminRepository.findById(1)).thenReturn(Optional.of(admin));
//	when(adminRepository.save(admin)).thenReturn(admin);
//	Admin result = adminServiceImpl.updateAdminDetails(admin, 1);
//	assertEquals(admin, result);
//	}
//
//	@Test(expected = AdminException.class)
//	public void testUpdateAdminDetailsNotFound() {
//	when(adminRepository.findById(2)).thenReturn(Optional.empty());
//	adminServiceImpl.updateAdminDetails(admin, 2);
//	}
	}

