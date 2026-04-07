package com.example.salesrelations.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleUpdateDTO {

    @NotBlank(message = "Role is required")
    private String role;

    public RoleUpdateDTO() {
    }

    public RoleUpdateDTO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}