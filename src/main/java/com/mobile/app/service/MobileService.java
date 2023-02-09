package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.MobileException;

public interface MobileService {

	public Mobile addMobileByCategoryId(Mobile mobile, Integer categoryid) throws MobileException;

	public Mobile addMobile(Mobile newMobile) throws MobileException;

	public Mobile getMobileById(Integer MobileId) throws MobileException;

	public String updateMobileDetails(Mobile mobile, Integer mobileId) throws MobileException;

	public Mobile deleteMobileById(Integer MobileId) throws MobileException;

	public List<Mobile> getAllMobiles();

	public List<Mobile> getMobilesByName(String mobileName) throws MobileException;

	public List<Mobile> getMobilesByMobileCost(Double cost) throws MobileException;

	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileException;

	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileException;

}
