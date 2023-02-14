package com.mobile.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Admin;
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

//	@PostMapping("admin/addCategory")
//	public Category addCategory(@Valid @RequestBody Category category)
//			throws CategoryException {
//
//		return adminService.addCategory(category);
//	}
//
//	@PostMapping("admin/addCategoryDetails/{id}")
//	public String updateCategoryDetails(@Valid @RequestBody Category category)
//			throws CategoryException {
//
//		return adminService.updateCategoryDetails(category);
//	}
//
//	@PostMapping("admin/addmobile/{id}")
//	public Mobile addMobile(@Valid @RequestBody Mobile mobile, @PathVariable("id") Integer id) throws CategoryException {
//
//		return adminService.addMobile(mobile, id);
//	}

//	@PutMapping("admin/updatemobilecost/{id}")
//	public Mobile changeMobileCostById(@RequestBody Mobile mobile, @PathVariable("id") Integer id)
//			throws MobileException {
//
//		return adminService.changeMobileCostById(mobile, id);
//	}

//	@PutMapping("admin/updatemobilequantity/{id}")
//	public Mobile changeMobileQuantityById(@RequestBody Mobile mobile, @PathVariable("id") Integer id)
//			throws MobileException {
//
//		return adminService.changeMobileQuantityById(mobile, id);
//	}

//	@PutMapping("admin/addorder/{id}")
//	public Orders addOrder(@RequestBody Orders orders, @PathVariable("id") Integer id) {
//
//		return adminService.addOrder(orders, id);
//	}

//	@GetMapping("admin/confirmOrder/{id}")
//	public String ConfirmOrder( @PathVariable("id") Integer id) throws MobileException {
//
//		return adminService.ConfirmOrder( id);
//	}
//	
//	@GetMapping("admin/cancelorder/{id}")
//	public String CancelOrder(@RequestBody Orders order, @PathVariable("id") Integer id) throws MobileException {
//
//		return adminService.CancelOrder(id);
//	}
//	@GetMapping("admin/checkCustomerStatusById/{id}")
//	public String checkCustomerStatusById(@PathVariable("id") Integer id) throws MobileException {
//
//		return adminService.checkCustomerStatusById(id);
//	}
//	@GetMapping("admin/getCustomerById/{id}")
//	public Customer getCustomerById( @PathVariable("id") Integer id) throws MobileException, CustomerException {
//
//		return adminService.getCustomerById(id);
//	}
//	@PostMapping("admin/updateAdminDetails/{id}")
//	public Admin updateAdminDetails(@Valid @RequestBody Admin admin, @PathVariable("id") Integer id)
//			throws  AdminException {
//
//		return adminService.updateAdminDetails(admin, id);
//	}
//	@GetMapping("admin/getAllCategories")
//	public List<Category> getAllCategories(){
//
//		return adminService.getAllCategories();
//	}
//	@GetMapping("admin/getAllCustomers")
//	public List<Customer> getAllCustomers(){
//
//		return adminService.getAllCustomers();
//	}
//	@GetMapping("/getAllMobiles")
//	public List<Mobile> getAllMobiles(){
//
//		return adminService.getAllMobiles();
//	}
//	@GetMapping("/getAllMobilesByCategory")
//	public List<Mobile> getAllMobilesByCategory( @PathVariable("id") Integer id){
//
//		return adminService.getAllMobilesByCategory(id);
//	}
//	@GetMapping("/getAllOrders")
//	public List<Orders> getAllOrders(){
//
//		return adminService.getAllOrders();
//	}
	
}
