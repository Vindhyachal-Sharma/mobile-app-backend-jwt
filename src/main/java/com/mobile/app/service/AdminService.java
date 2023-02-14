package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Category;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.AdminException;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.exception.OrderException;

public interface AdminService {
//	Category addCategory(Category category) throws CategoryException;
//
//	Mobile addMobileToCategoryByCategoryId(Mobile mobile, Integer id) throws CategoryException;
//
//	String updateCategoryName(Category category) throws CategoryException;
//
//	String updatemobileDetails(Mobile mobile) throws MobileException;
//
//	String updateMobileCostById(Mobile mobile) throws MobileException;

	Admin updateAdminDetails(Admin admin) throws AdminException;

//	Customer getCustomerById(Integer id) throws CustomerException;
//
//	Category getCategoryById(Integer id) throws CategoryException;
//
//	List<Category> getAllCategories();
//
//	List<Customer> getAllCustomers();
//
//	List<Mobile> getAllMobiles();
//
//	List<Mobile> getAllMobilesByCategory(Integer id) throws MobileException, CategoryException;
//
//	List<Orders> getAllOrders();
//	Mobile changeMobileQuantityById(Mobile mobile);
//	String ConfirmOrder( Integer id);
//	String CancelOrder( Integer id);
//	String checkCustomerStatusById(Integer id);
}
