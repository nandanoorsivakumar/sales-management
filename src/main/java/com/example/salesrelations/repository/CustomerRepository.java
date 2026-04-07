package com.example.salesrelations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.salesrelations.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}