package com.example.salesmanagement.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.salesmanagement.dto.LoginRequest;
import com.example.salesmanagement.dto.LoginResponse;
import com.example.salesmanagement.response.ApiResponse;
import com.example.salesmanagement.security.JwtService;
import com.example.salesmanagement.service.impl.CustomUserDetailsService;

import jakarta.validation.Valid;

/**
 * AuthController
 *
 * Purpose:
 * Exposes login API.
 *
 * Flow:
 * 1. User sends username + password
 * 2. AuthenticationManager validates credentials
 * 3. If valid, generate JWT token
 * 4. Return token to client
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {

        /*
         * Step 1:
         * Validate username and password using Spring Security
         */
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        /*
         * Step 2:
         * Load user details from database
         */
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        /*
         * Step 3:
         * Generate JWT token
         */
        String jwtToken = jwtService.generateToken(userDetails);

        /*
         * Step 4:
         * Return token in response
         */
        ApiResponse<LoginResponse> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Login successful",
                new LoginResponse(jwtToken)
        );

        return ResponseEntity.ok(response);
    }
}