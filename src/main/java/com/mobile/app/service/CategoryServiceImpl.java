package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MobileService mobileService;

	@Override
	public Category addCategory(Category category) throws CategoryException {
		Category optCategory = this.categoryRepository.findByName(category.getName());
		if (optCategory != null) {
			throw new CategoryException("Category Already Exists");
		} else {
			return this.categoryRepository.save(category);
		}
	}

	@Override
	public String updateCategory(Category category) throws CategoryException {
//		Category existingCategory = this.categoryRepository.findByName(category.getName());
//		if (existingCategory == null) {
//			throw new CategoryException("Category Not Found ");
//		}
//		
		Optional<Category> existingCategory = this.categoryRepository.findById(category.getId());
		if (existingCategory.isEmpty())
			throw new CategoryException("Category Not Found");
		Category updatedCategory = existingCategory.get();
		updatedCategory.setId(existingCategory.get().getId());
		updatedCategory.setName(category.getName());
		updatedCategory.setMobiles(category.getMobiles());
		this.categoryRepository.save(updatedCategory);

		return "Category Updated Successfully";
	}

	@Override
	public Category getCategoryById(Integer id) throws CategoryException {
		Optional<Category> categoryOpt = this.categoryRepository.findById(id);
		if (categoryOpt.isEmpty())
			throw new CategoryException("Category Not Found");

		return categoryOpt.get();
	}

	@Override
	public String deleteCategoryById(Integer id) throws CategoryException {
		Optional<Category> optCategory = this.categoryRepository.findById(id);
		if (optCategory.isEmpty())
			throw new CategoryException("Category does not exists to delete !");
		Category category = optCategory.get();
		this.categoryRepository.deleteById(id);
		return "Id Deleted Successfully";
	}

	@Override
	public List<Category> getAllCategories() {

		return this.categoryRepository.findAll();
	}

	@Override
	public String removeMobileFromCategoryById(Integer categoryId, Integer mobileId)
			throws MobileException, CategoryException {
		Mobile removedMobile = null;
		Category category = categoryRepository.findById(categoryId).get();

		if (category == null) {
			throw new CategoryException("Category Not Found");
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
				throw new MobileException("Requested Mobile" + mobileId + "Not found");
		}
		return "Mobile deleted Succesfully";
	}

}
