package com.example.salesrelations.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderDTO {

    @NotBlank(message = "Order number is required")
    private String orderNumber;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    public OrderDTO() {
    }

    public OrderDTO(String orderNumber, LocalDate orderDate, Double amount, String status, Long customerId, Long employeeId) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.amount = amount;
        this.status = status;
        this.customerId = customerId;
        this.employeeId = employeeId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}