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

public interface AdminService {

	Category addCategory(Category category, Integer id) throws CategoryException;

	String addCategoryDetails(Category category, Integer id) throws CategoryException;

	Mobile addMobile(Mobile mobile, Integer id) throws MobileException;

	String updatemobileDetails(Mobile mobile, Integer id) throws MobileException;

	Mobile changeMobileCostById(Mobile mobile, Integer id);

	Mobile changeMobileQuantityById(Mobile mobile, Integer id);

	Orders addOrder(Orders order, Integer id);

	String ConfirmOrder(Orders order, Integer id);

	String CancelOrder(Orders order, Integer id);

	// Admin beACustomer(Admin admin, Integer id);

	String checkCustomerStatusById(Customer customer, Integer id);

	Customer getCustomerById( Integer id) throws CustomerException;

	Admin updateAdminDetails(Admin admin, Integer id) throws AdminException;

	List<Category> getAllCategories();
	
	Category getCategoryById(Integer id) throws CategoryException;

	List<Customer> getAllCustomers();

	List<Mobile> getAllMobiles();

	List<Mobile> getAllMobilesByCategory();

	List<Orders> getAllOrders();

}
