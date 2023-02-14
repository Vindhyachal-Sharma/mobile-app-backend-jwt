package com.mobile.app.service;

import com.mobile.app.entity.Admin;
import com.mobile.app.exception.AdminNotFoundException;

public interface AdminService {


	Admin updateAdminDetails(Admin admin) throws AdminNotFoundException;

}
