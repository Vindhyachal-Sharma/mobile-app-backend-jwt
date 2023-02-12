package com.mobile.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileRepository mobileRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Mobile addMobileToCategoryByCategoryId(Mobile mobile, Integer categoryId) throws CategoryException {
				Category category=categoryService.getCategoryById(categoryId);
		//		Optional<Category> optCategory = this.categoryRepository.findById(categoryId);
		if(category==null) {
		  throw new CategoryException("Category does not exist!!");	
		}
		Category foundCategory = category;
		Mobile newMobile = this.mobileRepository.save(mobile);// add new mobile to DB
		foundCategory.getMobiles().add(newMobile); // add managed mobile to catogory list
		this.categoryRepository.save(foundCategory); // persist category 
		return newMobile;
	}
	
	@Override
	public Mobile addMobileToCart(Mobile mobile,Integer categoryId, Integer cartId) throws CategoryException, MobileException {
		
		Optional<Category> category = categoryRepository.findById(categoryId);
		if (category.isEmpty()) {
			throw new CategoryException("Category Not Found");

		}
		Category foundCategory =category.get();
		Mobile newMobile=addMobileToCategoryByCategoryId(mobile,categoryId);
		foundCategory.getMobiles().add(newMobile);
		return newMobile;
	}



	@Override
	public Mobile getMobileById(Integer id) throws MobileException {
		Optional<Mobile> optMobile = this.mobileRepository.findById(id);
		if (optMobile.isEmpty()) {
			throw new MobileException("Mobile id not found "+id);
		}
		return optMobile.get();
	}

	@Override
	public String updateMobileDetails(Mobile mobile)throws MobileException {
		Optional<Mobile> existingMobile = mobileRepository.findById(mobile.getMobileId());
		if(!existingMobile.isPresent())
		{
			throw new MobileException("Mobile not found");
		}
		Mobile updateMobile=existingMobile.get();
		updateMobile.setMobileId(mobile.getMobileId());
		updateMobile.setMobileName(mobile.getMobileName());
		updateMobile.setMfd(mobile.getMfd());
		updateMobile.setModelNumber(mobile.getModelNumber());
		updateMobile.setCompanyName(mobile.getCompanyName());
		updateMobile.setMobileCost(mobile.getMobileCost());
	    this.mobileRepository.save(updateMobile);
	    
	    return "Mobile details updated successfully";
	}
	@Override
	public String deleteMobileById(Integer mobileId)throws MobileException {
		Optional<Mobile> optMobile = this.mobileRepository.findById(mobileId);
		if (optMobile.isEmpty())
			throw new MobileException("Mobile id does not exists to delete !");
		Mobile mobile = optMobile.get();
		this.mobileRepository.deleteById(mobileId);
		return "Mobile Deleted Successfully";
		
	}


	@Override
	public List<Mobile> getAllMobiles() {
		return this.mobileRepository.findAll();
	}

	@Override
	public List<Mobile> getMobilesByName(String mobileName) throws MobileException {
		Iterable<Mobile> mobileIterable  = mobileRepository.findByMobileName(mobileName);
		List<Mobile> mobileList=new ArrayList<>();
		mobileIterable.forEach(n ->{
			mobileList.add(n);
		});
		if(mobileList.isEmpty()) {
			throw new MobileException("No Mobile Found By Given Name");
		}
		return mobileList;
	
	}

	@Override
	public List<Mobile> getMobilesByMobileCost(Double cost) throws MobileException {
		Iterable<Mobile> mobileIterable  = mobileRepository.findByMobileCost(cost);
		List<Mobile> mobileList=new ArrayList<>();
		mobileIterable.forEach(n ->{
			mobileList.add(n);
		});
		if(mobileList.isEmpty()) {
			throw new MobileException("No Mobile Found By Given Name");
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileException {
		Iterable<Mobile> mobileIterable  = mobileRepository.findByModelNumber(modelNumber);
		List<Mobile> mobileList=new ArrayList<>();
		mobileIterable.forEach(n ->{
			mobileList.add(n);
		});
		if(mobileList.isEmpty()) {
			throw new MobileException("No Mobile Found By Given Name");
		}
		return mobileList;
	}

	@Override
	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileException {
		Iterable<Mobile> mobileIterable  = mobileRepository.findByCompanyName(companyName);
		List<Mobile> mobileList=new ArrayList<>();
		mobileIterable.forEach(n ->{
			mobileList.add(n);
		});
		if(mobileList.isEmpty()) {
			throw new MobileException("No Mobile Found By Given Name");
		}
		return mobileList;
	}

}
