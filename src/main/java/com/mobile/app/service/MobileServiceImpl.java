package com.mobile.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.MobileException;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileRepository mobileRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Mobile addMobileByCategoryId(Mobile mobile, Integer id) throws MobileException {
		Optional<Category> optCategory = this.categoryRepository.findById(id);
		if(optCategory.isEmpty()) {
		  throw new MobileException("Category does not exist!!");	
		}
		Category foundCategory = optCategory.get();
		Mobile newMobile = this.mobileRepository.save(mobile);// add new mobile to DB
		foundCategory.getMobiles().add(newMobile); // add managed mobile to catogory list
		this.categoryRepository.save(foundCategory); // persist category
		return newMobile;
	}

	@Override
	public Mobile addMobile(Mobile mobile) throws MobileException {
		Optional<Category> categoryOpt = this.categoryRepository.findById(mobile.getCategory().getId());
		if(categoryOpt.isEmpty()) {
			throw new MobileException("Need to add this category of mobile");
		}
		Category category = categoryOpt.get();

		Mobile mob = new Mobile(mobile.getMobileId(), mobile.getMobileName(), mobile.getMobileCost(), mobile.getMfd(),
				mobile.getModelNumber(), mobile.getCompanyName(), category);

		return this.mobileRepository.save(mob);
		
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
	public String updateMobileDetails(Mobile mobile,Integer mobileId)throws MobileException {
		Optional<Mobile> existingMobile = mobileRepository.findById(mobileId);
		if(!existingMobile.isPresent())
		{
			throw new MobileException("Mobile not found");
		}
		Mobile updateMobile=existingMobile.get();
		updateMobile.setMobileId(mobileId);
		updateMobile.setMobileCost(mobile.getMobileCost());
	    this.mobileRepository.save(updateMobile);
	    
	    return "Mobile details updated successfully";
	}
	@Override
	public Mobile deleteMobileById(Integer MobileId)throws MobileException {
		Optional<Mobile> optMobile = this.mobileRepository.findById(MobileId);
		if (optMobile.isEmpty())
			throw new MobileException("Mobile id does not exists to delete !");
		Mobile mobile = optMobile.get();
		this.mobileRepository.delete(mobile);
		return mobile;
		
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
