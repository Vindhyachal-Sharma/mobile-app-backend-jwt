package com.mobile.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Cart;
import com.mobile.app.entity.Category;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.AdminException;
import com.mobile.app.exception.CategoryException;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.exception.MobileException;
import com.mobile.app.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	// ------------------------------AdminServices---------------
//	Category addCategory(Category category, Integer id) throws CategoryException;
//	String addCategoryDetails(Category category, Integer id) throws CategoryException;
//	Mobile addMobile(Mobile mobile, Integer id) throws MobileException;
//	String updatemobileDetails(Mobile mobile, Integer id) throws MobileException;
//	Mobile changeMobileCostById(Mobile mobile, Integer id);
//	Mobile changeMobileQuantityById(Mobile mobile, Integer id);
//	Orders addOrder(Orders order, Integer id);
//	String ConfirmOrder(Orders order, Integer id);
//	String CancelOrder(Orders order, Integer id);
//	// Admin beACustomer(Admin admin, Integer id);
//	String checkCustomerStatusById(Customer customer, Integer id);
//	Customer getCustomerById( Integer id) throws CustomerException;
//	Admin updateAdminDetails(Admin admin, Integer id) throws AdminException;
//	List<Category> getAllCategories();
//	Category getCategoryById(Integer id) throws CategoryException;
//	List<Customer> getAllCustomers();
//	List<Mobile> getAllMobiles();
//	List<Mobile> getAllMobilesByCategory();
//	List<Orders> getAllOrders();

	@PostMapping("admin/addCategory/{id}")
	public Category addCategory(@RequestBody Category category, @PathVariable("id") Integer id)
			throws CategoryException {

		return adminService.addCategory(category, id);
	}

}
