package com.mobile.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.service.CategoryService;
import com.mobile.app.service.MobileService;

@RestController
public class MobileController {

	@Autowired
	private MobileService mobileService;

	@Autowired
	private CategoryService categoryService;

//	----------------------------------------MobileService----------------
//	public Mobile addMobileByCategory(Mobile mobile, Integer categoryId) throws MobileException;
//	public Mobile addMobile(Mobile newMobile) throws MobileException;
//	public Mobile getMobileById(Integer MobileId) throws MobileException;
//	public String updateMobileDetails(Mobile mobile, Integer mobileId) throws MobileException;
//	public Mobile deleteMobileById(Integer MobileId) throws MobileException;
//	public List<Mobile> getAllMobiles();
//	public List<Mobile> getMobilesByName(String mobileName) throws MobileException;
//	public List<Mobile> getMobilesByPrice(Integer cost) throws MobileException;
//	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileException;
//	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileException;

	@PostMapping("/mobile/{categoryId}")
	public Mobile addMobileByCategory(@Valid @RequestBody Mobile mobile, @PathVariable("categoryId") Integer categoryId)
			throws CategoryException {
		return mobileService.addMobileToCategoryByCategoryId(mobile, categoryId);
	}
//	@PostMapping("/mobile")
//	public Mobile addMobile(@RequestBody Mobile mobile)throws MobileException
//	{
//		return mobileService.addMobile(mobile);
//	}

	@GetMapping("/mobiles/{id}")
	public Mobile getMobileById(@PathVariable("id") Integer id) throws MobileException {

		return mobileService.getMobileById(id);
	}

	@PutMapping("/updateMobile")
	public String updateMobileDetails(@Valid @RequestBody Mobile mobile) throws MobileException {

		return mobileService.updateMobileDetails(mobile);
	}

	@DeleteMapping("/mobile/{mobileId}")
	public String deleteMobileById( @RequestBody Integer categoryId, @PathVariable("mobileId") Integer mobileId)
			throws MobileException, CategoryException {

		return this.categoryService.removeMobileFromCategoryById(categoryId, mobileId);
	}

	@GetMapping("/mobiles")
	public List<Mobile> getAllMobiles() {
		return mobileService.getAllMobiles();
	}

	@GetMapping("/mobileN/{mobileName}")
	public List<Mobile> getMobilesByName(@PathVariable("mobileName") String mobileName) throws MobileException {
		return mobileService.getMobilesByName(mobileName);
	}

	@GetMapping("/mobileC/{cost}")
	public List<Mobile> getMobilesByPrice(@PathVariable("cost") Double cost) throws MobileException {
		return mobileService.getMobilesByMobileCost(cost);
	}

	@GetMapping("/mobilemn/{modelNumber}")
	public List<Mobile> getMobilesByModelNumber(@PathVariable("modelNumber") String modelNumber)
			throws MobileException {
		return mobileService.getMobilesByModelNumber(modelNumber);
	}

	@GetMapping("/mobileCn/{companyName}")
	public List<Mobile> getMobilesByCompanyName(@PathVariable("companyName") String companyName)
			throws MobileException {
		return mobileService.getMobilesByCompanyName(companyName);
	}

}
