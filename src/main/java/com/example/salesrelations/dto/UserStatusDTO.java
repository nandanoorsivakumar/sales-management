package com.example.salesrelations.dto;

import jakarta.validation.constraints.NotNull;

public class UserStatusDTO {

    @NotNull(message = "Enabled status is required")
    private Boolean enabled;

    public UserStatusDTO() {
    }

    public UserStatusDTO(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}