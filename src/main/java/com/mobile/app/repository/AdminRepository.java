package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{

}
