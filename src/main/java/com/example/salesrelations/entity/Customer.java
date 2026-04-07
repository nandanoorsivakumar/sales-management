package com.example.salesrelations.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String email;
    private String phone;
    private String city;

    /****
     * Parent side of Customer -> Orders relationship
     * This side will be serialized
     */
    @JsonManagedReference(value = "customer-order")
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(Long id, String customerName, String email, String phone, String city, List<Order> orders) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}