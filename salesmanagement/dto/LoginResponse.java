package com.example.salesmanagement.dto;

/**
 * LoginResponse DTO
 *
 * Sent back to client after successful login.
 * Contains JWT token.
 */
public class LoginResponse {

    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}