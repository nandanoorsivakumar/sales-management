package com.example.salesmanagement.repository;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.salesmanagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	boolean existsByName(String name);
	Employee findByName(String name);
	Employee findByNameIgnoreCase(String name);
	List<Employee> findByNameContaining(String keyword);
	Employee findTopByOrderBySalaryDesc();
	List<Employee> findByDepartmentAndSalaryGreaterThan(String department, Double salary);
	
	List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    Employee findFirstByName(String name);

    boolean existsByDepartment(String department);
    
    
    // NEw
   List<Employee> findByActiveTrue();
    
    List<Employee> findByActiveFalse();
    
    List<Employee> findByJoiningDateAfter(LocalDate date);
    
    List<Employee> findByLastLoginBetween(LocalDateTime start, LocalDateTime end);
    
    List<Employee> findTop3ByOrderBySalaryDesc();

}