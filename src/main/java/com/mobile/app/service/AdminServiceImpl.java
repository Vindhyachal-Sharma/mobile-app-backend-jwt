package com.mobile.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Admin;
import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin updateAdminDetails(Admin updateAdmin) throws AdminNotFoundException {
		Optional<Admin> adminOpt = this.adminRepository.findById(updateAdmin.getId());
		if (adminOpt.isEmpty())
			throw new AdminNotFoundException("Admin Id does not exists to update.");

		Admin admin = adminOpt.get();
		admin.setName(updateAdmin.getName());
		admin.setMobileNo(updateAdmin.getMobileNo());
		admin.setEmail(updateAdmin.getEmail());

		return this.adminRepository.save(updateAdmin);
	}

}
 