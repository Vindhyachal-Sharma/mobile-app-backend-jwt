package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select user from User where userName = :user_name and password=:password")
	public User getUserByUsernameAndPassword( @Param("user_name")String userName,@Param("password")String password);

	public User findByUserName(String userName);

	

}
