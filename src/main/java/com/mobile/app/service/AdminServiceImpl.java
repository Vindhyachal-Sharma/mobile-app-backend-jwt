package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.mobile.app.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

//	@Autowired
//	private AdminRepository adminRepository;
//
//	@Autowired
//	private CategoryService categoryService;
//
//	@Autowired
//	private MobileService mobileService;
//
//	@Autowired
//	private CustomerService customerService;
//
//	@Autowired
//	private OrderService orderService;
//
//	@Override
//	public Category addCategory(Category category) throws CategoryException {
//		return categoryService.addCategory(category);
//	}
//
//	@Override
//	public String updateCategoryDetails(Category category) throws CategoryException {
//		return categoryService.updateCategory(category);
//	}
//
//	@Override
//	public Mobile addMobile(Mobile mobile, Integer id) throws CategoryException {
//
//		return mobileService.addMobileToCategoryByCategoryId(mobile, id);
//	}
//
//	@Override
//	public String updatemobileDetails(Mobile mobile) throws MobileException {
//		return mobileService.updateMobileDetails(mobile);
//	}
//
//	@Override
//	public Mobile changeMobileCostById(Mobile mobile, Integer id) {
//		return null;
//	}
//
//	@Override
//	public Mobile changeMobileQuantityById(Mobile mobile, Integer id) {
//		return null;
//	}
//
////	@Override
////	public Orders addMobileToOrderByCustomerId(Integer mobileId, Integer customerId) throws CustomerException, MobileException, OrderException {
////		return orderService.addMobileToOrderByCustomerId(mobileId,customerId);
////	}
//
//	@Override
//	public String ConfirmOrder( Integer id) {
//		return null;
//	}
//
//	@Override
//	public String CancelOrder( Integer id) {
//		return null;
//	}
//
//	@Override
//	public String checkCustomerStatusById( Integer id) {
//		return null;
//	}
//
//	@Override
//	public Customer getCustomerById(Integer id) throws CustomerException {
//
//		return customerService.getCustomerById(id);
//	}
//
//	@Override
//	public Admin updateAdminDetails(Admin updateAdmin, Integer id) throws AdminException {
//		Optional<Admin> adminOpt = this.adminRepository.findById(updateAdmin.getId());
//		if (adminOpt.isEmpty())
//			throw new AdminException("Admin id does not exist to update.");
//
//		Admin admin = adminOpt.get();
//		admin.setName(updateAdmin.getName());
//		admin.setMobileNo(updateAdmin.getMobileNo());
//		admin.setEmail(updateAdmin.getEmail());
//
//		return this.adminRepository.save(updateAdmin);
//	}
//
//	@Override
//	public List<Category> getAllCategories() {
//
//		return categoryService.getAllCategories();
//	}
//
//	@Override
//	public Category getCategoryById(Integer id) throws CategoryException {
//
//		return categoryService.getCategoryById(id);
//	}
//
//	@Override
//	public List<Customer> getAllCustomers() {
//
//		return customerService.getAllCustomers();
//	}
//
//	@Override
//	public List<Mobile> getAllMobiles() {
//		// TODO Auto-generated method stub
//		return mobileService.getAllMobiles();
//	}
//
//	@Override
//	public List<Mobile> getAllMobilesByCategory(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Orders> getAllOrders() {
//		// TODO Auto-generated method stub
//		return orderService.getAllOrders();
//	}
//
//	@Override
//	public Orders addMobileToOrderByCustomerId(Integer mobileId, Integer id)
//			throws CustomerException, MobileException, OrderException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
