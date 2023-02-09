package com.mobile.app.controller;

import java.util.List;

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
	public Category registerCategory(@RequestBody Category category, @PathVariable("id") Integer id)
			throws CategoryException {

		return categoryService.addCategory(category, id);
	}

	@GetMapping("/category/{id}")
	public Category getCategoryById(@PathVariable("id") Integer id) throws CategoryException {

		return categoryService.getCategoryById(id);
	}

	@PutMapping("/category")
	public String updateCategory(@RequestBody Category updateCategory, @PathVariable("id") Integer id)
			throws CategoryException {

		return categoryService.updateCategory(updateCategory,id);
	}

	@DeleteMapping("/category/{id}")
	public Category deleteCategoryById(@PathVariable("id") Integer categoryId) throws CategoryException {

		return this.categoryService.deleteCategoryById(categoryId);

	}

	@GetMapping("/allCategories/")
	public List<Category> getAllCategorys() {
		return categoryService.getAllCategories();
	}
}
