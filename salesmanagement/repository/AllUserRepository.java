package com.example.salesmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.AllUser;

public interface AllUserRepository extends JpaRepository<AllUser, Long> {

    Optional<AllUser> findByUsername(String username);
}