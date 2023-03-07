package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;

public interface CategoryService {

	Category addCategory(Category category) throws CategoryNotFoundException;

	String updateCategory(Integer categoryId,Category category) throws CategoryNotFoundException;

	Category getCategoryById(Integer id) throws CategoryNotFoundException;

	String deleteCategoryById(Integer id) throws CategoryNotFoundException;

	List<Category> getAllCategories();

	String removeMobileFromCategoryById(Integer categoryId, Integer mobileId)
			throws MobileNotFoundException, CategoryNotFoundException;

	Category getCategoryByName(String name) throws CategoryNotFoundException;

}
