package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;

public interface MobileService {

	public Mobile addMobileToCategoryByCategoryId(Mobile mobile, Integer categoryid) throws CategoryException;


	public Mobile getMobileById(Integer MobileId) throws MobileException;

	public String updateMobileDetails(Mobile mobile) throws MobileException;

	public String deleteMobileById(Integer MobileId) throws MobileException;

	public List<Mobile> getAllMobiles();

	public List<Mobile> getMobilesByName(String mobileName) throws MobileException;

	public List<Mobile> getMobilesByMobileCost(Double cost) throws MobileException;

	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileException;

	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileException;


	Mobile addMobileToCart(Mobile mobile, Integer categoryId, Integer cartId) throws CategoryException, MobileException;

}
