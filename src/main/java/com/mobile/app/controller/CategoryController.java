package com.mobile.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") Integer categoryId, HttpServletRequest request)
			throws CategoryNotFoundException {

		return categoryService.getCategoryById(categoryId);
	}

	@GetMapping("/name/{categoryName}")
	public Category getCategoryByName(@PathVariable("categoryName") String categoryName, HttpServletRequest request)
			throws CategoryNotFoundException {

		return categoryService.getCategoryByName(categoryName);
	}

	@GetMapping("/categories")
	public List<Category> getAllCategorys() {
		return categoryService.getAllCategories();
	}

}
