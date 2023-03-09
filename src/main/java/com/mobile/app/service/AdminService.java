package com.mobile.app.service;

import com.mobile.app.entity.Admin;
import com.mobile.app.exception.AdminNotFoundException;

public interface AdminService {


	Admin updateAdminDetails(Integer adminId,Admin admin) throws AdminNotFoundException;

	Admin addAdmin(Admin newAdmin) throws AdminNotFoundException;

	Admin getAdminById(Integer adminId) throws AdminNotFoundException;

}
