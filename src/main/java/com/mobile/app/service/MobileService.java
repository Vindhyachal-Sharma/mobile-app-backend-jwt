package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;

public interface MobileService {

	public Mobile getMobileById(Integer MobileId) throws MobileNotFoundException;

	public String updateMobileDetails(Integer mobileId,Mobile mobile) throws MobileNotFoundException;

	public String deleteMobileById( Integer MobileId) throws MobileNotFoundException;

	public List<Mobile> getAllMobiles();

	

	public List<Mobile> getMobilesByMobileCost(Double cost) throws MobileNotFoundException;

	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileNotFoundException;

	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileNotFoundException;

	List<Mobile> getMobilesByCategoryName(String categoryName) throws MobileNotFoundException, CategoryNotFoundException;
	
	
	List<Mobile> getMobilesByCategoryId(Integer categoryId) throws MobileNotFoundException, CategoryNotFoundException;

	Mobile addMobileToCategoryByCategoryId(Mobile mobile, Integer categoryId) throws CategoryNotFoundException;

	
	
	List<Mobile> getMobilesByName(String mobileName) throws MobileNotFoundException;

}
