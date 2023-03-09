package com.mobile.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Customer;
import com.mobile.app.exception.AdminNotFoundException;
import com.mobile.app.exception.CustomerNotFoundException;
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
	public Admin updateAdminDetails(Integer adminId, Admin updateAdmin) throws AdminNotFoundException {
		Optional<Admin> adminOpt = this.adminRepository.findById(adminId);
		if (adminOpt.isEmpty())
			throw new AdminNotFoundException("Admin Id does not exists to update.");

		Admin admin = adminOpt.get();
		admin.setName(updateAdmin.getName());
		return this.adminRepository.save(updateAdmin);
	}
	@Override
	public Admin getAdminById(Integer adminId) throws AdminNotFoundException {

		Optional<Admin> admin = adminRepository.findById(adminId);
		if (admin.isEmpty())
			throw new AdminNotFoundException("Admin id not found :" + adminId);

		return admin.get();
	}
	
	
}
