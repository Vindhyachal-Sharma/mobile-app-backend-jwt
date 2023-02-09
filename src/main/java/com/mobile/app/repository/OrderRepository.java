package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

}
