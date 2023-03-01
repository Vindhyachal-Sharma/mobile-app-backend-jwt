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
	public Admin addAdmin(Admin admin) throws AdminNotFoundException {
		Admin optAdmin = this.adminRepository.findByEmail(admin.getEmail());
		Admin optMobile = this.adminRepository.findByMobileNo(admin.getMobileNo());

		if (optAdmin != null) {
			throw new AdminNotFoundException("Email already registered");
		}
		if (optMobile != null) {
			throw new AdminNotFoundException("Mobile number already registered");
			
		}
		admin.setRole("Admin");
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdminDetails(Admin updateAdmin) throws AdminNotFoundException {
		Optional<Admin> adminOpt = this.adminRepository.findById(updateAdmin.getUserId());
		if (adminOpt.isEmpty())
			throw new AdminNotFoundException("Admin Id does not exists to update.");

		Admin admin = adminOpt.get();
		admin.setName(updateAdmin.getName());
		admin.setRole(admin.getRole());
		admin.setMobileNo(updateAdmin.getMobileNo());
		admin.setEmail(updateAdmin.getEmail());

		return this.adminRepository.save(updateAdmin);
	}

}
