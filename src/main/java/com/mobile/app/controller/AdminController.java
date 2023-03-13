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
import com.mobile.app.entity.Payment;
import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.exception.CategoryNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
import com.mobile.app.exception.JwtTokenMalformedException;
import com.mobile.app.exception.JwtTokenMissingException;
import com.mobile.app.exception.MobileNotFoundException;
import com.mobile.app.exception.OrderNotFoundException;
import com.mobile.app.exception.PaymentNotFoundException;
import com.mobile.app.exception.UserNotFoundException;
import com.mobile.app.jwt.JwtUtil;
import com.mobile.app.service.AdminService;
import com.mobile.app.service.CategoryService;
import com.mobile.app.service.CustomerService;
import com.mobile.app.service.MobileService;
import com.mobile.app.service.OrderService;
import com.mobile.app.service.PaymentService;

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

	@Autowired
	private PaymentService paymentService;

	

	@PostMapping("/category")
	public Category addCategory(@Valid @RequestBody Category category, HttpServletRequest request) throws CategoryNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException
			 {
		JwtUtil.validateToken(request);
		return categoryService.addCategory(category);
	}

	@PostMapping("/register")
	public Admin registerAdmin(@Valid @RequestBody Admin admin, HttpServletRequest request) throws UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException, AdminNotFoundException   {
		JwtUtil.validateToken(request);
		return adminService.addAdmin(admin);
	}

	@GetMapping("/admin/{adminId}")
	public Admin getAdminById(@PathVariable("adminId") Integer adminId, HttpServletRequest request)
			throws AdminNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return adminService.getAdminById(adminId);
	}

	@PutMapping("/category/{categoryId}")
	public String updateCategoryDetails(@PathVariable("categoryId") Integer categoryId,
			@Valid @RequestBody Category category, HttpServletRequest request) throws CategoryNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return categoryService.updateCategory(categoryId, category);
	}

	@PostMapping("/mobile/{categoryId}")
	public Mobile addMobileToCategoryByCategoryId(@Valid @RequestBody Mobile mobile,
			@PathVariable("categoryId") Integer categoryId, HttpServletRequest request)
			throws CategoryNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return mobileService.addMobileToCategoryByCategoryId(mobile, categoryId);
	}

	@PutMapping("/mobile/{mobileId}")
	public String updateMobile(@PathVariable("mobileId") Integer mobileId, @RequestBody Mobile mobile,
			HttpServletRequest request) throws MobileNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return mobileService.updateMobileDetails(mobileId, mobile);
	}

	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Integer customerId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.getCustomerById(customerId);
	}

	@GetMapping("/mobiles/{mobileId}")
	public Mobile getMobileById(@PathVariable("mobileId") Integer mobileId, HttpServletRequest request)
			throws MobileNotFoundException {

		return mobileService.getMobileById(mobileId);
	}

	@GetMapping("/customer/{emailId}")
	public Customer getCustomerByEmail(@PathVariable("emailId") String emailId, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.getCustomerByEmail(emailId);
	}

	@GetMapping("/mobNo/{mobNo}")
	public Customer getCustomerByMobile(@PathVariable("mobNo") String mobNo, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.getCustomerByMobileNo(mobNo);
	}

	@GetMapping("/username/{username}")
	public Customer getCustomerByUsername(@PathVariable("username") String username, HttpServletRequest request)
			throws CustomerNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.getCustomerByUsername(username);
	}

	@PutMapping("/{adminId}")
	public Admin updateAdminDetails(@PathVariable("adminId") Integer adminId, @Valid @RequestBody Admin admin,
			HttpServletRequest request) throws AdminNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return adminService.updateAdminDetails(adminId, admin);
	}

	@GetMapping("/categories")
	public List<Category> getAllCategories(HttpServletRequest request) {

		return categoryService.getAllCategories();
	}

	@GetMapping("/customers")
	public List<Customer> getAllCustomers(HttpServletRequest request) throws UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return customerService.getAllCustomers();
	}

	@GetMapping("/mobiles")
	public List<Mobile> getAllMobiles(HttpServletRequest request) {

		return mobileService.getAllMobiles();
	}

	@GetMapping("/mobile/{categoryId}")
	public List<Mobile> getAllMobilesByCategory(@PathVariable("categoryId") Integer categoryId,
			HttpServletRequest request) throws MobileNotFoundException, CategoryNotFoundException {

		return mobileService.getMobilesByCategoryId(categoryId);
	}

	@GetMapping("/orders")
	public List<Orders> getAllOrders(HttpServletRequest request) throws UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return orderService.getAllOrders();
	}

	@GetMapping("/category/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryNotFoundException {

		return categoryService.getCategoryById(categoryId);
	}

	@DeleteMapping("/mobiles/{categoryId}/{mobileId}")
	public String removeMobileFromCategoryById(@PathVariable("categoryId") Integer categoryId,
			@PathVariable("mobileId") Integer mobileId) throws MobileNotFoundException, CategoryNotFoundException {
		
		return this.categoryService.removeMobileFromCategoryById(categoryId, mobileId);
	}

	@DeleteMapping("/mobile/{mobileId}")
	public String removeMobileById(@PathVariable("mobileId") Integer mobileId) throws MobileNotFoundException {

		return this.mobileService.deleteMobileById(mobileId);
	}

	@PutMapping("/order/{orderId}")
	public String cancelOrder(@PathVariable("orderId") Integer orderId,HttpServletRequest request) throws OrderNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return this.orderService.cancelOrderById(orderId);
	}

	@GetMapping("/order/{id}")
	public Orders getOrderById(@PathVariable("id") Integer orderId,HttpServletRequest request) throws OrderNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return orderService.getOrderById(orderId);
	}

	@GetMapping("/payment/{paymentId}")
	public Payment getPaymentById(@PathVariable("paymentId") Integer paymentId,HttpServletRequest request) throws PaymentNotFoundException, UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return paymentService.getPaymentById(paymentId);
	}

	@PutMapping("/payment/{paymentId}")
	public Payment updatePaymentDetails(@PathVariable("paymentId") Integer paymentId,
			@Valid @RequestBody Payment payment, HttpServletRequest request) throws UserNotFoundException, JwtTokenMalformedException, JwtTokenMissingException {
		JwtUtil.validateToken(request);
		return paymentService.updatePayment(payment, paymentId);
	}

}
