package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select user from Users user where user_name = :user_name and password=:password")
//	public User getUserByUsernameAndPassword( String userName, String password);

	public User findByUserName(String userName);

//	
//  public User signIn(User user);
//  public User signOut(User user);
//  public UserchangePassword(long id, User user);
//	
}
