package com.example.salesrelations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private LocalDate orderDate;
    private Double amount;
    private String status;

    /****
     * Child side of Order -> Customer relationship
     * This side will not again serialize customer.orders
     */
    @JsonBackReference(value = "customer-order")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /****
     * Child side of Order -> Employee relationship
     * This side will not again serialize employee.orders
     */
    @JsonBackReference(value = "employee-order")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Order() {
    }

    public Order(Long id, String orderNumber, LocalDate orderDate, Double amount, String status, Customer customer, Employee employee) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.amount = amount;
        this.status = status;
        this.customer = customer;
        this.employee = employee;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}