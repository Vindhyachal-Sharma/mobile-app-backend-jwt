package com.mobile.app.service;

import java.util.List;

import com.mobile.app.entity.Admin;

public interface AdminService {

	Admin AddAdmin(Admin admin, Integer id);

	Admin updateAdminDetails(Admin admin);

	Admin deleteAdminById(Admin admin, Integer id);

	Admin getAdminById(Admin admin, Integer id);

	List<Admin> getAllAdmins();

}
