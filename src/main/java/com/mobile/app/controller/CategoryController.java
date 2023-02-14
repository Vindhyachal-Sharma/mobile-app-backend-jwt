package com.mobile.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

//	-----------------------------------------CategoryService------------------
//	Category addCategory(Category category,Integer id) throws CategoryException;
//	String updateCategory(Category category,Integer id) throws CategoryException;
//	Category getCategoryById(Integer id) throws CategoryException;
//	Category deleteCategoryById(Integer id);
//	List<Category>getAllCategory();

	@PostMapping("/category")
	public Category registerCategory(@Valid @RequestBody Category category)
			throws CategoryException {

		return categoryService.addCategory(category);
	}

	@GetMapping("/category/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryException {

		return categoryService.getCategoryById(categoryId);
	}

	@PutMapping("/category/name")
	public String updateCategory(@Valid @RequestBody Category updateCategory)
			throws CategoryException {

		return categoryService.updateCategory(updateCategory);
	}

	@DeleteMapping("/category/delete/{id}")
	public String deleteCategoryById(@PathVariable("id") Integer categoryId) throws CategoryException {

		return this.categoryService.deleteCategoryById(categoryId);

	}

	@GetMapping("/categories")
	public List<Category> getAllCategorys() {
		return categoryService.getAllCategories();
	}
}
