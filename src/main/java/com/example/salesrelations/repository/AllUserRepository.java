package com.example.salesrelations.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesrelations.entity.AllUser;

public interface AllUserRepository extends JpaRepository<AllUser, Long> {

    Optional<AllUser> findByUsername(String username);

    boolean existsByUsername(String username);
}