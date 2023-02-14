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

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin updateAdminDetails(Admin updateAdmin) throws AdminException {
		Optional<Admin> adminOpt = this.adminRepository.findById(updateAdmin.getId());
		if (adminOpt.isEmpty())
			throw new AdminException("Admin Id does not exists to update.");

		Admin admin = adminOpt.get();
		admin.setName(updateAdmin.getName());
		admin.setMobileNo(updateAdmin.getMobileNo());
		admin.setEmail(updateAdmin.getEmail());

		return this.adminRepository.save(updateAdmin);
	}

	



}
