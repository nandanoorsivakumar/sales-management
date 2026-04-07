package com.example.salesrelations.dto;

import java.time.LocalDate;

public class OrderResponseDTO {

    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private Double amount;
    private String status;
    private Long customerId;
    private String customerName;
    private Long employeeId;
    private String employeeName;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, String orderNumber, LocalDate orderDate, Double amount, String status,
                            Long customerId, String customerName,
                            Long employeeId, String employeeName) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.amount = amount;
        this.status = status;
        this.customerId = customerId;
        this.customerName = customerName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}