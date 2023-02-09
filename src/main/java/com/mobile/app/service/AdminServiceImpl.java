package com.mobile.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Admin;
import com.mobile.app.repository.AdminRepository;

@Service
public class AdminServiceImpl  implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin AddAdmin(Admin admin, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin updateAdminDetails(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin deleteAdminById(Admin admin, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getAdminById(Admin admin, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
