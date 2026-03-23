package com.example.salesmanagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Schema(description = "Employee Data Transfer Object")
public class EmployeeDTO {

	 @Schema(description = "Employee ID", example = "1")
    private Long id;

	@Schema(description = "Employee Name", example = "Siva")
    @NotBlank(message = "Name is Required")
    private String name;

	@Schema(description = "Department", example = "IT")
    @NotBlank(message = "Department is required")
    private String department;

	@Schema(description = "Salary", example = "50000")
    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;

    private LocalDateTime lastLogin;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String department, Double salary,
                       Boolean active, LocalDate joiningDate, LocalDateTime lastLogin) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.active = active;
        this.joiningDate = joiningDate;
        this.lastLogin = lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}