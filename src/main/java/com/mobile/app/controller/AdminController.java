package com.mobile.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Category;
import com.mobile.app.entity.Customer;
import com.mobile.app.entity.Mobile;
import com.mobile.app.entity.Orders;
import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.service.AdminService;
import com.mobile.app.service.CategoryService;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.MobileService;
import com.mobile.app.service.OrderService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MobileService mobileService;

	@Autowired
	private CustomerService customerService;

	@PostMapping("/category")
	public Category addCategory(@Valid @RequestBody Category category, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException {
		
		return categoryService.addCategory(category);
	}

	@PostMapping("/register")
	public Admin registerAdmin(@Valid @RequestBody Admin newAdmin)
			throws UserNotFoundException, AdminNotFoundException {

		return adminService.addAdmin(newAdmin);
	}

	@PostMapping("/category/name")
	public String updateCategoryDetails(@Valid @RequestBody Category category, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException {
		
		return categoryService.updateCategory(category);
	}

	@PostMapping("/mobile/{categoryName}")
	public Mobile addMobileToCategoryByCategoryName(@Valid @RequestBody Mobile mobile,
			@PathVariable("categoryName") String categoryName, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException {
		
		return mobileService.addMobileToCategoryByCategoryName(mobile, categoryName);
	}

	@PutMapping("/mobile/cost")
	public String updateMobileCostById(@RequestBody Mobile mobile, HttpServletRequest request)
			throws MobileNotFoundException, UserNotFoundException {
		
		return mobileService.updateMobileDetails(mobile);
	}

	@PutMapping("/mobile")
	public String updateMobile(@RequestBody Mobile mobile, HttpServletRequest request)
			throws MobileNotFoundException, UserNotFoundException {
		
		return mobileService.updateMobileDetails(mobile);
	}

	@GetMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws MobileNotFoundException, CustomerNotFoundException, UserNotFoundException {
		
		return customerService.getCustomerById(customerId);
	}

	@PostMapping("/admin")
	public Admin updateAdminDetails(@Valid @RequestBody Admin admin, HttpServletRequest request)
			throws AdminNotFoundException, UserNotFoundException {
		
		return adminService.updateAdminDetails(admin);
	}

	@GetMapping("/categories")
	public List<Category> getAllCategories(HttpServletRequest request) throws UserNotFoundException {
		
		return categoryService.getAllCategories();
	}

	@GetMapping("/customers")
	public List<Customer> getAllCustomers(HttpServletRequest request) throws UserNotFoundException {
//		login.validateToken(request,"Admin");
		return customerService.getAllCustomers();
	}

	@GetMapping("/mobiles")
	public List<Mobile> getAllMobiles(HttpServletRequest request) throws UserNotFoundException {
		
		return mobileService.getAllMobiles();
	}

	@GetMapping("/mobile/{categoryId}")
	public List<Mobile> getAllMobilesByCategory(@PathVariable("categoryId") Integer categoryId,
			HttpServletRequest request)
			throws MobileNotFoundException, CategoryNotFoundException, UserNotFoundException {
		
		return mobileService.getMobilesByCategoryId(categoryId);
	}

	@GetMapping("/orders")
	public List<Orders> getAllOrders(HttpServletRequest request) throws UserNotFoundException {
		
		return orderService.getAllOrders();
	}

}
