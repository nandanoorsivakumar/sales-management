package com.example.salesrelations.dto;

import jakarta.validation.constraints.NotBlank;

public class ChangePasswordDTO {

    @NotBlank(message = "New password is required")
    private String newPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}