package com.mobile.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin("*")
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

	
	
	@PutMapping("/category/{categoryId}")
	public String updateCategoryDetails(@PathVariable("categoryId") Integer categoryId,@Valid @RequestBody Category category, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException {
		
		return categoryService.updateCategory(categoryId,category);
	}

	@PostMapping("/mobile/{categoryId}")
	public Mobile addMobileToCategoryByCategoryId(@Valid @RequestBody Mobile mobile,
			@PathVariable("categoryId") Integer categoryId, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException {
		
		return mobileService.addMobileToCategoryByCategoryId(mobile, categoryId);
	}

	

	@PutMapping("/mobile/{mobileId}")
	public String updateMobile(@PathVariable("mobileId") Integer mobileId,@RequestBody Mobile mobile, HttpServletRequest request)
			throws MobileNotFoundException, UserNotFoundException {
		
		return mobileService.updateMobileDetails(mobileId,mobile);
	}

	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws MobileNotFoundException, CustomerNotFoundException, UserNotFoundException {
		
		return customerService.getCustomerById(customerId);
	}
	@GetMapping("/mobiles/{mobileId}")
	public Mobile getMobileById(@PathVariable("mobileId") Integer mobileId, HttpServletRequest request)
			throws MobileNotFoundException, CustomerNotFoundException, UserNotFoundException {
		
		return mobileService.getMobileById(mobileId);
	}
	@GetMapping("/customer/{emailId}")
	public Customer getCustomerByEmail(@PathVariable("emailId") String emailId, HttpServletRequest request)
			throws MobileNotFoundException, CustomerNotFoundException, UserNotFoundException {
		
		return customerService.getCustomerByEmail(emailId);
	}
	@GetMapping("/mobNo/{mobNo}")
	public Customer getCustomerByMobile(@PathVariable("mobNo") String mobNo, HttpServletRequest request)
			throws MobileNotFoundException, CustomerNotFoundException, UserNotFoundException {
		
		return customerService.getCustomerByMobileNo(mobNo);
	}
	@GetMapping("/username/{username}")
	public Customer getCustomerByUsername(@PathVariable("username") String username, HttpServletRequest request)
			throws MobileNotFoundException, CustomerNotFoundException, UserNotFoundException {
		
		return customerService.getCustomerByUsername(username);
	}

	@PutMapping("/{adminId}")
	public Admin updateAdminDetails(@PathVariable("adminId") Integer adminId,@Valid @RequestBody Admin admin, HttpServletRequest request)
			throws AdminNotFoundException, UserNotFoundException {
		
		return adminService.updateAdminDetails(adminId,admin);
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
	
	@DeleteMapping("/mobiles/{categoryId}/{mobileId}")
	public String removeMobileFromCategoryById(@PathVariable("categoryId") Integer categoryId, @PathVariable("mobileId") Integer mobileId)
			throws MobileNotFoundException, CategoryNotFoundException {

		return this.categoryService.removeMobileFromCategoryById(categoryId, mobileId);
	}
	
	@DeleteMapping("/mobile/{mobileId}")
	public String removeMobileById(  @PathVariable("mobileId") Integer mobileId)
			throws MobileNotFoundException, CategoryNotFoundException {

		return this.mobileService.deleteMobileById(mobileId);
	}
}
