package com.example.salesrelations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesrelations.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByEmployeeId(Long employeeId);
}