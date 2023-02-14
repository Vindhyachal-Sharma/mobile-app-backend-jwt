package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobile.app.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
