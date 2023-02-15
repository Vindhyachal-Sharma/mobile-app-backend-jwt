package com.mobile.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobile.app.entity.Admin;
import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.repository.AdminRepository;
import com.mobile.app.service.AdminService;
@SpringBootTest
class AdminTestImplTest {
	
	@Autowired 
	AdminService adminService;
	
	@Autowired
	AdminRepository adminRepository;

	@Test
	public void testUpdateAdmin_Success() throws AdminNotFoundException {
		Integer adminId = 1;
		Admin expectedAdmin = new Admin();
		expectedAdmin.setId(adminId);
		expectedAdmin.setName("John Doe");
		expectedAdmin.setMobileNo("1234567890");
		expectedAdmin.setEmail("johndoe@example.com");
		when(adminRepository.findById(adminId)).thenReturn(Optional.of(expectedAdmin));
		when(adminRepository.save(expectedAdmin)).thenReturn(expectedAdmin);

		Admin updatedAdmin = new Admin();
		updatedAdmin.setId(adminId);
		updatedAdmin.setName("John Doe");
		updatedAdmin.setMobileNo("1234567890");
		updatedAdmin.setEmail("johndoe@example.com");
		
		Admin actualAdmin = adminService.updateAdminDetails(updatedAdmin);

		assertEquals(expectedAdmin, actualAdmin);
		assertEquals(expectedAdmin.getName(), expectedAdmin.getName());
		assertEquals(expectedAdmin.getMobileNo(), expectedAdmin.getMobileNo());
		assertEquals(expectedAdmin.getEmail(), expectedAdmin.getEmail());
	}
}
