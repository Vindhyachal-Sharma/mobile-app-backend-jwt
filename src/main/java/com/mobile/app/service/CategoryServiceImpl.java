package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MobileService mobileService;

	@Override
	public Category addCategory(Category category) throws CategoryNotFoundException {
		Category optCategory = this.categoryRepository.findByName(category.getName());
		if (optCategory != null) {
			throw new CategoryNotFoundException("Category Already Exists");
		} else {
			return this.categoryRepository.save(category);
		}
	}

	@Override
	public String updateCategory(Integer categoryId,Category category) throws CategoryNotFoundException {

		Category existingCategory = getCategoryById(categoryId);
		if (existingCategory==null)
			throw new CategoryNotFoundException("Category Not Found");
		Category updatedCategory = existingCategory;
		updatedCategory.setId(existingCategory.getId());
		updatedCategory.setName(category.getName());
		updatedCategory.setMobiles(existingCategory.getMobiles());
		this.categoryRepository.save(updatedCategory);

		return "Category Updated Successfully";
	}

	@Override
	public Category getCategoryById(Integer id) throws CategoryNotFoundException {
		Optional<Category> categoryOpt = this.categoryRepository.findById(id);
		if (categoryOpt.isEmpty())
			throw new CategoryNotFoundException("Category Not Found");

		return categoryOpt.get();
	}
	@Override
	public Category getCategoryByName(String name) throws CategoryNotFoundException {
		Category categoryOpt = this.categoryRepository.findByName(name);
		if (categoryOpt==null)
			throw new CategoryNotFoundException("Category Not Found");

		return categoryOpt;
	}

	@Override
	public String deleteCategoryById(Integer id) throws CategoryNotFoundException {
		Optional<Category> optCategory = this.categoryRepository.findById(id);
		if (optCategory.isEmpty())
			throw new CategoryNotFoundException("Category does not exists to delete !");

		this.categoryRepository.deleteById(id);
		return "Id Deleted Successfully";
	}

	@Override
	public List<Category> getAllCategories() {

		return this.categoryRepository.findAll();
	}

	@Override
	public String removeMobileFromCategoryById(Integer categoryId, Integer mobileId)
			throws MobileNotFoundException, CategoryNotFoundException {
		Mobile removedMobile = null;
		Category category = categoryRepository.findById(categoryId).get();

		if (category == null) {
			throw new CategoryNotFoundException("Category Not Found");
		} else {
			if (category.getMobiles() != null) {
				for (Mobile mobile : category.getMobiles()) {
					if (mobile.getMobileId() == mobileId)
						removedMobile = mobile;
				}
				category.getMobiles().remove(removedMobile);
				mobileService.deleteMobileById(mobileId);
				categoryRepository.save(category);
			} else
				throw new MobileNotFoundException("Requested Mobile" + mobileId + "Not found");
		}
		return "Mobile deleted Succesfully";
	}

}
