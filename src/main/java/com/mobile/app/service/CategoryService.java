package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryException;

public interface CategoryService {
	
	Category addCategory(Category category,Integer id) throws CategoryException;
	
	String updateCategory(Category category,Integer id) throws CategoryException;
	
	Category getCategoryById(Integer id) throws CategoryException;
	
	Category deleteCategoryById(Integer id);
	
	List<Category>getAllCategories();

}
