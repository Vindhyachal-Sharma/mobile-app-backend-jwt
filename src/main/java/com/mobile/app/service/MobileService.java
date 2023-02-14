package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;

public interface MobileService {

	public Mobile addMobileToCategoryByCategoryId(Mobile mobile, Integer categoryid) throws CategoryNotFoundException;


	public Mobile getMobileById(Integer MobileId) throws MobileNotFoundException;

	public String updateMobileDetails(Mobile mobile) throws MobileNotFoundException;

	public String deleteMobileById(Integer MobileId) throws MobileNotFoundException;

	public List<Mobile> getAllMobiles();

	public List<Mobile> getMobilesByName(String mobileName) throws MobileNotFoundException;

	public List<Mobile> getMobilesByMobileCost(Double cost) throws MobileNotFoundException;

	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileNotFoundException;

	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileNotFoundException;


	List<Mobile> getMobilesByCategoryId(Integer CategoryId) throws MobileNotFoundException, CategoryNotFoundException;

}
