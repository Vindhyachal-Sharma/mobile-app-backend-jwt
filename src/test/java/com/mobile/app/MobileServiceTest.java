package com.mobile.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.service.MobileService;

@SpringBootTest
class MobileServiceTest {
	@Autowired
	private MobileService mobileService;

	@Test
	void addMobileToCategoryByCategoryIdExceptionTest() 
	{
	   LocalDate date = LocalDate.parse("2023-02-10");
	   Mobile mobile=new Mobile(10,"Oppo",15000.0,date,"A13","Oppo");
	   assertThrows(CategoryNotFoundException.class,()->mobileService.addMobileToCategoryByCategoryId(mobile,30));
	}
	@Test
	void updateMobileDetailsTest()
	{
		LocalDate date = LocalDate.parse("2023-02-10");
		Mobile mobile=new Mobile(10,"Oppo",15000.0,date,"A13","Oppo");
		assertThrows(MobileNotFoundException.class,()->mobileService.updateMobileDetails(mobile));
	}
	@Test
	void getMobileByIdTest()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobileById(10));
	}
	@Test
	void getMobileByIdTest1()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobileById(11));
	}
	@Test
	void getMobileByIdExceptionTest()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobileById(15));
	}
	@Test
	void getAllMobilesTest()
	{
		assertNotNull(mobileService.getAllMobiles());
	}
	@Test
	void getMobilesByNameTest()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobilesByName("Realme"));
	}
	@Test
	void deleteMobileByIdTest()throws MobileNotFoundException
	{
		assertThrows(MobileNotFoundException.class,()->mobileService.deleteMobileById(10));
	}
	@Test
	void getMobilesByMobileCostTest()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobilesByMobileCost(15000.0));
	}
	@Test
	void getMobilesByModel()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobilesByModelNumber("Realme"));
	}
	@Test
	void getMobilesByCompanyNameTest()throws MobileNotFoundException
	{
		assertNotNull(mobileService.getMobilesByCompanyName("Realme"));
	}

}
