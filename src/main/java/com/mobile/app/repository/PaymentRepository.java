package com.mobile.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
