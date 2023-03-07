package com.mobile.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;
/************************************************************************************
 * @author Amrutha 
 * Description It is a service class that provides the services
 *         for add,get,delete,update and viewing all the mobiles Version 1.0
 *         Created Date 08-FEB-2023
 ************************************************************************************/
@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileRepository mobileRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;
	
	
	/************************************************************************************
	 * Method:- addMobileToCategory 
	 * Description:- add the mobile into the category
	 * @paramTo mobile - mobile will be added to category
	 * @returns mobile - true, if add otherwise throws CategoryExceptionException
	 * @throws CategoryException - if Category not found 
	 * Created By - Amrutha
	 * Created Date - 8-FEB-2023
	 ************************************************************************************/

	
	@Override
	public Mobile addMobileToCategoryByCategoryId(Mobile mobile, Integer categoryId) throws CategoryNotFoundException {
		Category category = categoryService.getCategoryById(categoryId);
		if (category == null) {
			throw new CategoryNotFoundException("Category does not exist!!");
		}
		Category foundCategory = category;
		Mobile newMobile = this.mobileRepository.save(mobile);// add new mobile to DB
		foundCategory.getMobiles().add(newMobile); // add managed mobile to catogory list
		this.categoryRepository.save(foundCategory); // persist category
		return newMobile;
	}
	/************************************************************************************
	 * Method: - getMobileById 
	 * Description: - get the mobiles using the id
	 * @paramTo mobile - gets themobile by using id
	 * @returns mobile - true,otherwise throws mobileException
	 * @throws MobileException - if Mobile not found 
	 * Created By - Amrutha 
	 * Created Date - 8-FEB-2023
	 ************************************************************************************/
	@Override
	public Mobile getMobileById(Integer id) throws MobileNotFoundException {
		Optional<Mobile> optMobile = this.mobileRepository.findById(id);
		if (optMobile.isEmpty()) {
			throw new MobileNotFoundException("Mobile id not found " + id);
		}
		return optMobile.get();
	}
	
	/************************************************************************************
	 * Method: - updateMobileDetails 
	 * Description: - To update the mobile details
	 * @paramTo mobileId - mobile details will be changed
	 * @returns mobile - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found 
	 * Created By - Amrutha 
	 * Created Date - 8-FEB-2023
	 ************************************************************************************/

	@Override
	public String updateMobileDetails(Integer mobileId,Mobile mobile) throws MobileNotFoundException {
		Mobile existingMobile = getMobileById(mobileId);
		if (existingMobile==null) {
			throw new MobileNotFoundException("Mobile not found");
		}
		Mobile updateMobile = existingMobile;
		updateMobile.setMobileId(existingMobile.getMobileId());
		updateMobile.setMobileName(mobile.getMobileName());
		updateMobile.setMfd(mobile.getMfd());
		updateMobile.setModelNumber(mobile.getModelNumber());
		updateMobile.setCompanyName(mobile.getCompanyName());
		updateMobile.setMobileCost(mobile.getMobileCost());
		updateMobile.setDescription(mobile.getDescription());
		this.mobileRepository.save(updateMobile);

		return "Mobile details updated successfully";
	}
	/************************************************************************************
	 * Method: - deleteMobileById
	 * Description: - deleting the mobile details
	 * @paramTo mobileId - going to delete the mobile
	 * @returns mobile - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found 
	 * Created By - Amrutha 
	 * Created Date - 8-FEB-2023
	 ************************************************************************************/
	@Override
	public String deleteMobileById( Integer mobileId) throws MobileNotFoundException {
		Optional<Mobile> optMobile = this.mobileRepository.findById(mobileId);
		if (optMobile.isEmpty())
			throw new MobileNotFoundException("Mobile id does not exists to delete !");
		Mobile mobile = optMobile.get();
		this.mobileRepository.deleteById(mobileId);
		return "Mobile Removed  Successfully";

	}
	/*************************************************************************************
	 * Method:           - getAllMobiles 
	 * Description:      - returns all the mobile
	 * @paramTo mobileId - gets all the mobiles
	 * @returns mobile   - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found 
	 * Created By        - Amrutha 
	 * Created Date      - 8-FEB-2023
	 ************************************************************************************/
	@Override
	public List<Mobile> getAllMobiles() {
		return this.mobileRepository.findAll();
	}
	/************************************************************************************
	 * Method:           - getMobilesByName 
	 * Description:      - returns the mobile by using the mobileName
	 * @paramTo mobileId - get mobiles by using the mobile name
	 * @returns mobile   - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found
	 *  Created By       - Amrutha 
	 *  Created Date     - 9-FEB-2023
	 ************************************************************************************/
	@Override
	public List<Mobile> getMobilesByName(String mobileName) throws MobileNotFoundException {
		Iterable<Mobile> mobileIterable = mobileRepository.findByMobileName(mobileName);
		List<Mobile> mobileList = new ArrayList<>();
		mobileIterable.forEach(n -> {
			mobileList.add(n);
		});
		if (mobileList.isEmpty()) {
			throw new MobileNotFoundException("No Mobile Found By Given Name");
		}
		return mobileList;

	}
	/************************************************************************************
	 * Method:           - getMobilesByMobileCost
	 * Description:      - returns the mobile by using the mobile cost
	 * @paramTo mobileId - get mobiles by using the mobile cost
	 * @returns mobile   - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found
	 *  Created By       - Amrutha 
	 *  Created Date     - 9-FEB-2023
	 ************************************************************************************/
	@Override
	public List<Mobile> getMobilesByMobileCost(Double cost) throws MobileNotFoundException {
		Iterable<Mobile> mobileIterable = mobileRepository.findByMobileCost(cost);
		List<Mobile> mobileList = new ArrayList<>();
		mobileIterable.forEach(n -> {
			mobileList.add(n);
		});
		if (mobileList.isEmpty()) {
			throw new MobileNotFoundException("No Mobile Found By Given Name");
		}
		return mobileList;
	}
	/************************************************************************************
	 * Method:           - getMobilesByModelNumber
	 * Description:      - returns the mobile by using the ModelNumber
	 * @paramTo mobileId - get mobiles by using the model number
	 * @returns mobile   - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found
	 *  Created By       - Amrutha 
	 *  Created Date     - 10-FEB-2023
	 ************************************************************************************/
	@Override
	public List<Mobile> getMobilesByModelNumber(String modelNumber) throws MobileNotFoundException {
		Iterable<Mobile> mobileIterable = mobileRepository.findByModelNumber(modelNumber);
		List<Mobile> mobileList = new ArrayList<>();
		mobileIterable.forEach(n -> {
			mobileList.add(n);
		});
		if (mobileList.isEmpty()) {
			throw new MobileNotFoundException("No Mobile Found By Given Name");
		}
		return mobileList;
	}

	/************************************************************************************
	 * Method:           -  getMobilesByCompanyName 
	 * Description:      - returns the mobile by using the CompanyName
	 * @paramTo mobileId - get mobiles by using the company name
	 * @returns mobile   - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found
	 *  Created By       - Amrutha 
	 *  Created Date     - 10-FEB-2023
	 ************************************************************************************/
	@Override
	public List<Mobile> getMobilesByCompanyName(String companyName) throws MobileNotFoundException {
		Iterable<Mobile> mobileIterable = mobileRepository.findByCompanyName(companyName);
		List<Mobile> mobileList = new ArrayList<>();
		mobileIterable.forEach(n -> {
			mobileList.add(n);
		});
		if (mobileList.isEmpty()) {
			throw new MobileNotFoundException("No Mobile Found By Given Name");
		}
		return mobileList;
	}
	/************************************************************************************
	 * Method:           -  getMobilesByCategoryId
	 * Description:      - returns the mobile by using the CompanyName
	 * @paramTo mobileId - get mobiles by using the category name
	 * @returns mobile   - true, if add otherwise throws MobileException
	 * @throws MobileException - if mobile not found
	 *  Created By       - Amrutha 
	 *  Created Date     - 10-FEB-2023
	 ************************************************************************************/
	@Override
	public List<Mobile> getMobilesByCategoryName(String categoryName)
			throws MobileNotFoundException, CategoryNotFoundException {
		Category category = categoryService.getCategoryByName(categoryName);
		List<Mobile> mobileList = new ArrayList<>();
		mobileList.addAll(category.getMobiles());
		if (mobileList.isEmpty()) {
			throw new MobileNotFoundException("No Mobiles Found for Given category");
		}
		return mobileList;
	}
	@Override
	public List<Mobile> getMobilesByCategoryId(Integer categoryId)
			throws MobileNotFoundException, CategoryNotFoundException {
		Category category = categoryService.getCategoryById(categoryId);
		List<Mobile> mobileList = new ArrayList<>();
		mobileList.addAll(category.getMobiles());
		if (mobileList.isEmpty()) {
			throw new MobileNotFoundException("No Mobiles Found for Given category");
		}
		return mobileList;
	}
	
	

}
