package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.User;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Category findByName(String categoryName);
}
