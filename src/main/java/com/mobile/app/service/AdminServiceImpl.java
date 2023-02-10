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
import com.mobile.app.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MobileService mobileService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Override
	public Category addCategory(Category category, Integer id) throws CategoryException {
		return categoryService.addCategory(category, id);
	}

	@Override
	public String addCategoryDetails(Category category, Integer id) throws CategoryException {
		return categoryService.updateCategory(category, id);
	}

	@Override
	public Mobile addMobile(Mobile mobile, Integer id) throws MobileException {

		return mobileService.addMobileByCategoryId(mobile, id);
	}

	@Override
	public String updatemobileDetails(Mobile mobile, Integer id) throws MobileException {
		return mobileService.updateMobileDetails(mobile, id);
	}

	@Override
	public Mobile changeMobileCostById(Mobile mobile, Integer id) {
		return null;
	}

	@Override
	public Mobile changeMobileQuantityById(Mobile mobile, Integer id) {
		return null;
	}

	@Override
	public Orders addOrder(Orders order, Integer id) {
		return orderService.addOrder(order);
	}

	@Override
	public String ConfirmOrder(Orders order, Integer id) {
		return null;
	}

	@Override
	public String CancelOrder(Orders order, Integer id) {
		return null;
	}

	@Override
	public String checkCustomerStatusById(Customer customer, Integer id) {
		return null;
	}

	@Override
	public Customer getCustomerById(Integer id) throws CustomerException {

		return customerService.getCustomerById(id);
	}

	@Override
	public Admin updateAdminDetails(Admin updateAdmin, Integer id) throws AdminException {
		Optional<Admin> adminOpt = this.adminRepository.findById(updateAdmin.getId());
		if (adminOpt.isEmpty())
			throw new AdminException("Admin id does not exist to update.");

		Admin admin = adminOpt.get();
		admin.setName(updateAdmin.getName());
		admin.setMobileNo(updateAdmin.getMobileNo());
		admin.setEmail(updateAdmin.getEmail());

		return this.adminRepository.save(updateAdmin);
	}

	@Override
	public List<Category> getAllCategories() {

		return categoryService.getAllCategories();
	}

	@Override
	public Category getCategoryById(Integer id) throws CategoryException {

		return categoryService.getCategoryById(id);
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerService.getAllCustomers();
	}

	@Override
	public List<Mobile> getAllMobiles() {
		// TODO Auto-generated method stub
		return mobileService.getAllMobiles();
	}

	@Override
	public List<Mobile> getAllMobilesByCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return orderService.getAllOrders();
	}

}
