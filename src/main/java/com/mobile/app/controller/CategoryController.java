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
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category")
	public Category registerCategory(@Valid @RequestBody Category category)
			throws CategoryNotFoundException {

		return categoryService.addCategory(category);
	}

	@GetMapping("/category/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryNotFoundException {

		return categoryService.getCategoryById(categoryId);
	}

	@PutMapping("/category/name")
	public String updateCategoryName(@Valid @RequestBody Category updateCategory)
			throws CategoryNotFoundException {

		return categoryService.updateCategory(updateCategory);
	}

	@DeleteMapping("/category/delete/{categoryId}")
	public String deleteCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryNotFoundException {

		return this.categoryService.deleteCategoryById(categoryId);

	}

	@GetMapping("/categories")
	public List<Category> getAllCategorys() {
		return categoryService.getAllCategories();
	}
}
