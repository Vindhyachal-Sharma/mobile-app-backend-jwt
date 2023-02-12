package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;

public interface CategoryService {
	
	Category addCategory(Category category) throws CategoryException;
	
	String updateCategory(Category category) throws CategoryException;
	
	Category getCategoryById(Integer id) throws CategoryException;
	
	String deleteCategoryById(Integer id) throws CategoryException;
	
	List<Category>getAllCategories();
	
	String removeMobileFromCategoryById(Integer categoryId,Integer mobileId)throws MobileException, CategoryException;
	
	

}
