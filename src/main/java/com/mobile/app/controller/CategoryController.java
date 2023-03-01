package com.mobile.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.ValidateToken;
import com.mobile.app.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	ValidateToken login;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") Integer categoryId,HttpServletRequest request) throws CategoryNotFoundException, UserNotFoundException {
		login.validateToken(request,"Admin");
		return categoryService.getCategoryById(categoryId);
	}
	@GetMapping("/{categoryName}")
	public Category getCategoryByName(@PathVariable("categoryName") String categoryName, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException {
		login.validateToken(request,"Admin");
		return categoryService.getCategoryByName(categoryName);
	}

	@GetMapping("/categories")
	public List<Category> getAllCategorys(){
		return categoryService.getAllCategories();
	}

	/*@PutMapping("/name")
	public String updateCategoryName(@Valid @RequestBody Category updateCategory)
			throws CategoryNotFoundException {

		return categoryService.updateCategory(updateCategory);
	}

	@DeleteMapping("/delete/{categoryId}")
	public String deleteCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryNotFoundException {

		return this.categoryService.deleteCategoryById(categoryId);

	}
	@PostMapping("/category")
	public Category registerCategory(@Valid @RequestBody Category category)
			throws CategoryNotFoundException {

		return categoryService.addCategory(category);
	}*/

}
