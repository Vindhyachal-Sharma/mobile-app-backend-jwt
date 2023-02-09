package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
