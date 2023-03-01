package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.Admin;
import com.mobile.app.entity.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByUserName(String userName);
	public Admin findByEmail(String email);
	public Admin findByMobileNo(String mobileNo);
}
