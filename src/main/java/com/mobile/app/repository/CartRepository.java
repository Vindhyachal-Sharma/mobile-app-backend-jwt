package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
