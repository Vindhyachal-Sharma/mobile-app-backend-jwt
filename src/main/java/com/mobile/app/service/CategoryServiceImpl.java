package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Category;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

		
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category,Integer id) throws CategoryException {
		Optional<Category> optCategory = this.categoryRepository.findById(id);
		if(optCategory.isPresent()) {
		  throw new CategoryException("Category Already Exists");	
		}
		else {
			return  this.categoryRepository.save(category);
		}
	}

	@Override
	public String updateCategory(Category category,Integer id) throws CategoryException {
		Optional<Category> existingCategory=categoryRepository.findById(id);
		if(!existingCategory.isPresent()) {
			throw new CategoryException("Category Not Found ");
		}
		Category updatedCategory = existingCategory.get();
		updatedCategory.setId(id);
		updatedCategory.setName(category.getName());
		updatedCategory.setMobiles(category.getMobiles());
		 this.categoryRepository.save(updatedCategory);
		 
		 return "Category Updated Successfully";
	}

	@Override
	public Category getCategoryById(Integer id) throws CategoryException {
		Optional<Category> categoryOpt = this.categoryRepository.findById(id);
		if(categoryOpt.isEmpty())
			throw new CategoryException("Category Not Found");
		
		return categoryOpt.get();
	}

	@Override
	public Category deleteCategoryById(Integer id) {
		return null;
	}

	@Override
	public List<Category> getAllCategories() {
		
		return this.categoryRepository.findAll();
	}
	
	

}
