package com.mobile.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;
import com.mobile.app.service.MobileServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MobileServiceImplTest {

	@InjectMocks
	private MobileServiceImpl mobileService;

	@Mock
	private MobileRepository mobileRepository;

	@Mock
	private CategoryRepository categoryRepository;

	private Mobile mobile;
	private Integer categoryId;
	private Integer cartId;

	@Test
	public void testAddMobileToCartSuccess() throws CategoryException, MobileException {
		Category category = new Category();
		category.setId(1);
		category.setName("Smartphones");
		category.getMobiles().add(mobile);

		when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.of(category));
		when(mobileService.addMobileToCategoryByCategoryId(any(Mobile.class), any(Integer.class))).thenReturn(mobile);

		Mobile result = mobileService.addMobileToCart(mobile, categoryId, cartId);
		assertNotNull(result);
		assertEquals(mobile.getMobileId(), result.getMobileId());
		assertEquals(mobile.getMobileName(), result.getMobileName());
	}

//	  @Test(expected = CategoryException.class)
//	  public void testAddMobileToCartCategoryNotFound() throws CategoryException, MobileException {
//	    when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.empty());
//	    mobileService.addMobileToCart(mobile, categoryId, cartId);
//	  }
//	}
//	  @Test
//	    public void getMobileById_Success() throws MobileException {
//	        // given
//	        int id = 1;
//	        List<Category>orders=new ArrayList<>();
//	        Mobile expectedMobile = new Mobile(id, "iPhone X", 1000.0,null,"ABC","Abc",orders);
//	        when(mobileRepository.findById(anyInt())).thenReturn(Optional.of(expectedMobile));
//
//	        // when
//	        Mobile actualMobile = mobileService.getMobileById(id);
//
//	        // then
//	        assertEquals(expectedMobile, actualMobile);
//	    }
//
//	    @Test(expected = MobileException.class)
//	    public void getMobileById_MobileNotFound() throws MobileException {
//	        // given
//	        int id = 1;
//	        when(mobileRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//	        // when
//	        mobileService.getMobileById(id);
//
//	        // then expected exception
//	    }
	@Test
	public void testUpdateMobileDetails() throws MobileException {
		// Given
		Mobile mobile = new Mobile();
		mobile.setMobileId(1);
		mobile.setMobileName("iPhone");
		mobile.setMfd(LocalDate.now());
		mobile.setModelNumber("11 Pro");
		mobile.setCompanyName("Apple");
		mobile.setMobileCost(999.99);

		// When
		when(mobileRepository.findById(1)).thenReturn(Optional.of(mobile));
		String result = mobileService.updateMobileDetails(mobile);

		// Then
		assertEquals("Mobile details updated successfully", result);
		verify(mobileRepository).save(mobile);
	}

	@Test
	public void testUpdateMobileDetailsMobileNotFound() throws MobileException {
		// Given
		Mobile mobile = new Mobile();
		mobile.setMobileId(1);
		mobile.setMobileName("iPhone");
		mobile.setMfd(LocalDate.now());
		mobile.setModelNumber("11 Pro");
		mobile.setCompanyName("Apple");
		mobile.setMobileCost(999.99);

		// When
		when(mobileRepository.findById(1)).thenReturn(Optional.empty());
		Exception exception = assertThrows(MobileException.class, () -> {
			mobileService.updateMobileDetails(mobile);
		});

		// Then
		assertEquals("Mobile not found", exception.getMessage());
		verify(mobileRepository, never()).save(mobile);
	}

	@Test
	public void deleteMobileById_MobileExists_DeletesMobileSuccessfully() throws MobileException, CategoryException {
		// Arrange
		Integer mobileId = 1;
		Mobile mobile = new Mobile();
		mobile.setMobileId(mobileId);
		mobile.setMobileName("iPhone");
		mobile.setCompanyName("Apple");
		mobileService.addMobileToCategoryByCategoryId(mobile,anyInt());
		Mobile optionalMobile = mobileService.getMobileById(anyInt());
		assertTrue(optionalMobile!=null);

		// Act
		String result = mobileService.deleteMobileById(mobileId);

		// Assert
		assertEquals("Mobile Deleted Successfully", result);
		optionalMobile = mobileService.getMobileById(mobileId);
		assertFalse(optionalMobile!=null);
	}

//	@Test(expected = MobileException.class)
//	public void deleteMobileById_MobileDoesNotExist_ThrowsMobileException() {
//		// Arrange
//		Integer mobileId = 1;
//
//		// Act
//		mobileService.deleteMobileById(mobileId);
//
//		// Assert
//		// The expected exception should be thrown
//	}
	
	 
	  @Test
	  public void testGetMobilesByName_Success() throws MobileException {
	    List<Mobile> expectedMobiles = new ArrayList<>();
	    Integer mobileId = 1;
		Mobile mobile = new Mobile();
		mobile.setMobileId(mobileId);
		mobile.setMobileName("iPhone");
		mobile.setCompanyName("Apple");
	    expectedMobiles.add(mobile);
	    expectedMobiles.add(mobile);
	   
	    when(mobileRepository.findByMobileName("iPhone")).thenReturn(expectedMobiles);
	   
	    List<Mobile> result = mobileService.getMobilesByName("iPhone");
	   
	    assertNotNull(result);
	    assertEquals(expectedMobiles, result);
	  }
	 
//	  @Test(expected = MobileException.class)
//	  public void testGetMobilesByName_MobileNotFound() throws MobileException {
//	    when(mobileRepository.findByMobileName("Samsung")).thenReturn(new ArrayList<>());
//	   
//	    mobileService.getMobilesByName("Samsung");
//	  }
//	}
	  @Test
	  public void testGetMobilesByMobileCost_Success() throws MobileException {
	    List<Mobile> expectedMobiles = new ArrayList<>();
	    Mobile mobile = new Mobile();
		mobile.setMobileId(anyInt());
		mobile.setMobileName("iPhone");
		mobile.setCompanyName("Apple");
	    expectedMobiles.add(mobile);
	    expectedMobiles.add(mobile);
	    when(mobileRepository.findByMobileCost(1000.0)).thenReturn(expectedMobiles);
	   
	    List<Mobile> result = mobileService.getMobilesByMobileCost(1000.0);
	   
	    assertNotNull(result);
	    assertEquals(expectedMobiles, result);
	  }
	 
//	  @Test(expected = MobileException.class)
//	  public void testGetMobilesByMobileCost_MobileNotFound() throws MobileException {
//	    when(mobileRepository.findByMobileCost(700.0)).thenReturn(new ArrayList<>());
//	   
//	    mobileService.getMobilesByMobileCost(700.0);
//	  }
	  
	  @Test
	  public void testGetMobilesByModelNumber_Success() throws MobileException {
	    List<Mobile> expectedMobiles = new ArrayList<>();
	    Mobile mobile = new Mobile();
		mobile.setMobileId(anyInt());
		mobile.setMobileName("iPhone");
		mobile.setCompanyName("Apple");
	    expectedMobiles.add(mobile);
	    expectedMobiles.add(mobile);
	   
	    when(mobileRepository.findByModelNumber("A1234")).thenReturn(expectedMobiles);
	   
	    List<Mobile> result = mobileService.getMobilesByModelNumber("A1234");
	   
	    assertNotNull(result);
	    assertEquals(expectedMobiles, result);
	  }
	 
//	  @Test(expected = MobileException.class)
//	  public void testGetMobilesByModelNumber_MobileNotFound() throws MobileException {
//	    when(mobileRepository.findByModelNumber("M1234")).thenReturn(new ArrayList<>());
//	   
//	    mobileService.getMobilesByModelNumber("M1234");
//	  }
	  @Test
	  public void testGetMobilesByCompanyNameSuccess() throws MobileException {
		  List<Mobile>mobiles=new ArrayList<>();
	  when(mobileRepository.findByCompanyName("Apple")).thenReturn(mobiles);
	  List<Mobile> result = mobileService.getMobilesByCompanyName("Apple");
	  assertEquals(2, result.size());
	  assertEquals("Apple", result.get(0).getCompanyName());
	  assertEquals("iPhone X", result.get(0).getMobileName());
	  assertEquals("Samsung", result.get(1).getCompanyName());
	  assertEquals("Galaxy S9", result.get(1).getMobileName());
	  }

//	  @Test(expected = MobileException.class)
//	  public void testGetMobilesByCompanyNameFailure() throws MobileException {
//	  when(mobileRepository.findByCompanyName("Nokia")).thenReturn(null);
//	  mobileService.getMobilesByCompanyName("Nokia");
//	  }
	  @Test
	  public void testGetAllMobilesSuccess() {
		  List<Mobile>mobiles=new ArrayList<>();
	  when(mobileRepository.findAll()).thenReturn(mobiles);
	  List<Mobile> result = mobileService.getAllMobiles();
	  assertEquals(2, result.size());
	  assertEquals("Apple", result.get(0).getCompanyName());
	  assertEquals("iPhone X", result.get(0).getMobileName());
	  assertEquals("Samsung", result.get(1).getCompanyName());
	  assertEquals("Galaxy S9", result.get(1).getMobileName());
	  }
}
