package com.mobile.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;
import com.mobile.app.service.CategoryService;
import com.mobile.app.service.MobileService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategoryServiceImplTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MobileRepository mobileRepository;

	@MockBean
	private MobileService mobileService;

	@Test
	void addCategory_ShouldAddCategory_WhenCategoryNameIsUnique() throws CategoryException {
// Arrange
		List<Mobile> mobiles = new ArrayList<>();
		Category category = new Category(1, "Electronics", mobiles);
		when(categoryRepository.findByName(category.getName())).thenReturn(null);
		when(categoryRepository.save(category)).thenReturn(category);

// Act
		Category addedCategory = categoryService.addCategory(category);

// Assert
		assertNotNull(addedCategory);
		assertEquals(category, addedCategory);
		verify(categoryRepository, times(1)).findByName(category.getName());
		verify(categoryRepository, times(1)).save(category);
	}

	@Test
	void addCategory_ShouldThrowCategoryException_WhenCategoryNameIsNotUnique() throws CategoryException {
// Arrange
		List<Mobile> mobiles = new ArrayList<>();
		Category category = new Category(1, "Electronics", mobiles);
		when(categoryRepository.findByName(category.getName())).thenReturn(category);

// Act & Assert
		assertThrows(CategoryException.class, () -> categoryService.addCategory(category));
		verify(categoryRepository, times(1)).findByName(category.getName());
		verify(categoryRepository, times(0)).save(category);
	}

	@Test
	public void testUpdateCategory_Success() throws CategoryException {
		Category category = new Category();
		category.setId(1);
		category.setName("category_name");
		Optional<Category> optionalCategory = Optional.of(category);
		when(categoryRepository.findById(1)).thenReturn(optionalCategory);
		when(categoryRepository.save(category)).thenReturn(category);
		String result = categoryService.updateCategory(category);
		assertEquals("Category Updated Successfully", result);
	}

//	@Test(expected = CategoryException.class)
//	public void testUpdateCategory_CategoryNotFound() throws CategoryException {
//		Category category = new Category();
//		category.setId(1);
//		category.setName("category_name");
//		Optional<Category> optionalCategory = Optional.empty();
//		when(categoryRepository.findById(1)).thenReturn(optionalCategory);
//		categoryService.updateCategory(category);
//	}
	@Test
	public void testGetCategoryByIdSuccess() throws CategoryException {
		Integer id = 1;
		Category category = new Category();
		category.setId(id);
		category.setName("Test Category");

		Optional<Category> optionalCategory = Optional.of(category);
		when(categoryRepository.findById(id)).thenReturn(optionalCategory);

		Category result = categoryService.getCategoryById(id);
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals("Test Category", result.getName());
	}

//	@Test(expected = CategoryException.class)
//	public void testGetCategoryByIdFailure() throws CategoryException {
//		Integer id = 100;
//		Optional<Category> optionalCategory = Optional.empty();
//		when(categoryRepository.findById(id)).thenReturn(optionalCategory);
//
//		categoryService.getCategoryById(id);
//	}
	@Test
	public void testDeleteCategoryByIdSuccess() throws CategoryException {
		Integer id = 1;
		Category category = new Category();
		category.setId(id);
		category.setName("Test Category");

		Optional<Category> optionalCategory = Optional.of(category);
		when(categoryRepository.findById(id)).thenReturn(optionalCategory);
		doNothing().when(categoryRepository).deleteById(id);

		String result = categoryService.deleteCategoryById(id);
		assertNotNull(result);
		assertEquals("Id Deleted Successfully", result);
	}

//	@Test(expected = CategoryException.class)
//	public void testDeleteCategoryByIdFailure() throws CategoryException {
//	  Integer id = 100;
//	  Optional<Category> optionalCategory = Optional.empty();
//	  when(categoryRepository.findById(id)).thenReturn(optionalCategory);
//
//	  categoryService.deleteCategoryById(id);
//	}
	@Test
	public void testGetAllCategoriesSuccess() {
		List<Category> categories = new ArrayList<>();
		Category category1 = new Category();
		category1.setId(1);
		category1.setName("Test Category 1");
		categories.add(category1);

		Category category2 = new Category();
		category2.setId(2);
		category2.setName("Test Category 2");
		categories.add(category2);

		when(categoryRepository.findAll()).thenReturn(categories);

		List<Category> result = categoryService.getAllCategories();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getId().intValue());
		assertEquals("Test Category 1", result.get(0).getName());
		assertEquals(2, result.get(1).getId().intValue());
		assertEquals("Test Category 2", result.get(1).getName());
	}

	@Test
	public void testRemoveMobileFromCategoryByIdSuccess() throws MobileException, CategoryException {
		Integer categoryId = 1;
		Integer mobileId = 10;

		Mobile mobile = new Mobile();
		mobile.setMobileId(mobileId);
		mobile.setMobileName("Test Mobile");
		List<Mobile> mobiles = new ArrayList<>();
		mobiles.add(mobile);

		Category category = new Category();
		category.setId(categoryId);
		category.setName("Test Category");
		category.setMobiles(mobiles);

		Optional<Category> optionalCategory = Optional.of(category);
		when(categoryRepository.findById(categoryId)).thenReturn(optionalCategory);
		doNothing().when(mobileService).deleteMobileById(mobileId);
		doNothing().when(categoryRepository).save(category);

		String result = categoryService.removeMobileFromCategoryById(categoryId, mobileId);
		assertNotNull(result);
		assertEquals("Mobile deleted Succesfully", result);
	}

//	@Test(expected = CategoryException.class)
//	public void testRemoveMobileFromCategoryByIdFailureCategoryNotFound() throws MobileException, CategoryException {
//		Integer categoryId = 100;
//		Integer mobileId = 10;
//		Optional<Category> optionalCategory = Optional.empty();
//		when(categoryRepository.findById(categoryId)).thenReturn(optionalCategory);
//
//		categoryService.removeMobileFromCategoryById(categoryId, mobileId);
//	}
//
//	@Test(expected = MobileException.class)
//	public void testRemoveMobileFromCategoryByIdFailureMobileNotFound() throws MobileException, CategoryException {
//		Integer categoryId = 1;
//		Integer mobileId = 100;
//
//		Category category = new Category();
//		category.setId(categoryId);
//		category.setName("Test Category");
//		category.setMobiles(null);
//
//		Optional<Category> optionalCategory = Optional.of(category);
//		when(categoryRepository.findById(categoryId)).thenReturn(optionalCategory);
//
//		categoryService.removeMobileFromCategoryById(categoryId, mobileId);
//	}
	
	
	@Test
	public void testAddMobileToCategoryByCategoryId() throws CategoryException {
	Category category = new Category();
	category.setName("Smartphones");

	Mobile mobile = new Mobile();
	mobile.setMobileName("Apple");
	mobile.setCompanyName("iPhone X");
	mobile.setMobileCost(1000.0);

	when(categoryService.getCategoryById(anyInt())).thenReturn(category);
	when(mobileRepository.save(any(Mobile.class))).thenReturn(mobile);

	Mobile addedMobile = mobileService.addMobileToCategoryByCategoryId(mobile, category.getId());

	assertEquals(mobile.getMobileName(), addedMobile.getMobileName());
	assertEquals(mobile.getCompanyName(), addedMobile.getCompanyName());
	assertEquals(mobile.getMobileCost(), addedMobile.getMobileCost(), 0.0);

	verify(categoryService, times(1)).getCategoryById(anyInt());
	verify(mobileRepository, times(1)).save(any(Mobile.class));
	}

//	@Test(expected = CategoryException.class)
//	public void testAddMobileToCategoryByCategoryId_CategoryNotFound() throws CategoryException {
//	Category category = new Category();
//	category.setName("Smartphones");
//
//	Mobile mobile = new Mobile();
//	mobile.setMobileName("Apple");
//	mobile.setCompanyName("iPhone X");
//	mobile.setMobileCost(1000.0);
//
//	when(categoryService.getCategoryById(anyInt())).thenReturn(null);
//
//	mobileService.addMobileToCategoryByCategoryId(mobile, category.getId());
//
//	verify(categoryService, times(1)).getCategoryById(anyInt());
//	verify(mobileRepository, times(0)).save(any(Mobile.class));
//	}​​

	  






}
