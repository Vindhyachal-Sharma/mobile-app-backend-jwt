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
import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.service.AdminService;
import com.mobile.app.service.CategoryService;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.MobileService;
import com.mobile.app.service.OrderService;

@RestController
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

	@PostMapping("/admin/category")
	public Category addCategory(@Valid @RequestBody Category category) throws CategoryNotFoundException {

		return categoryService.addCategory(category);
	}

	@PostMapping("/admin/category/name")
	public String updateCategoryDetails(@Valid @RequestBody Category category) throws CategoryNotFoundException {

		return categoryService.updateCategory(category);
	}

	@PostMapping("/admin/mobile/{categoryId}")
	public Mobile addMobileToCategoryByCategoryId(@Valid @RequestBody Mobile mobile,
			@PathVariable("categoryId") Integer categoryId) throws CategoryNotFoundException {

		return mobileService.addMobileToCategoryByCategoryId(mobile, categoryId);
	}

	@PutMapping("/admin/mobile/cost")
	public String updateMobileCostById(@RequestBody Mobile mobile) throws MobileNotFoundException {

		return mobileService.updateMobileDetails(mobile);
	}

	@GetMapping("/admin/customer/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Integer customerId)
			throws MobileNotFoundException, CustomerNotFoundException {

		return customerService.getCustomerById(customerId);
	}

	@PostMapping("/admin")
	public Admin updateAdminDetails(@Valid @RequestBody Admin admin) throws AdminNotFoundException {

		return adminService.updateAdminDetails(admin);
	}

	@GetMapping("/admin/categories")
	public List<Category> getAllCategories() {

		return categoryService.getAllCategories();
	}

	@GetMapping("/admin/customers")
	public List<Customer> getAllCustomers() {

		return customerService.getAllCustomers();
	}

	@GetMapping("/admin/mobiles")
	public List<Mobile> getAllMobiles() {

		return mobileService.getAllMobiles();
	}

	@GetMapping("/admin/mobile/{categoryId}")
	public List<Mobile> getAllMobilesByCategory(@PathVariable("categoryId") Integer categoryId)
			throws MobileNotFoundException, CategoryNotFoundException {

		return mobileService.getMobilesByCategoryId(categoryId);
	}

	@GetMapping("/admin/orders")
	public List<Orders> getAllOrders() {

		return orderService.getAllOrders();
	}

}
