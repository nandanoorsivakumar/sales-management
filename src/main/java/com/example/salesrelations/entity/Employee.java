package com.example.salesrelations.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private Double salary;

    /****
     * Parent side of Employee -> Orders relationship
     * This side will be serialized
     */
    @JsonManagedReference(value = "employee-order")
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

    public Employee() {
    }

    public Employee(Long id, String name, String department, Double salary, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.orders = orders;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}