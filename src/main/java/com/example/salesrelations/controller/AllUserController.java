package com.example.salesrelations.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.salesrelations.dto.AllUserDTO;
import com.example.salesrelations.dto.ChangePasswordDTO;
import com.example.salesrelations.dto.RoleUpdateDTO;
import com.example.salesrelations.dto.UserStatusDTO;
import com.example.salesrelations.response.ApiResponse;
import com.example.salesrelations.service.AllUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class AllUserController {

    private final AllUserService allUserService;

    public AllUserController(AllUserService allUserService) {
        this.allUserService = allUserService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AllUserDTO>> createUser(@Valid @RequestBody AllUserDTO allUserDTO) {
        AllUserDTO createdUser = allUserService.createUser(allUserDTO);

        ApiResponse<AllUserDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "User created successfully",
                createdUser
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AllUserDTO>>> getAllUsers() {
        List<AllUserDTO> users = allUserService.getAllUsers();

        ApiResponse<List<AllUserDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Users fetched successfully",
                users
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AllUserDTO>> getUserById(@PathVariable Long id) {
        AllUserDTO user = allUserService.getUserById(id);

        ApiResponse<AllUserDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User fetched successfully",
                user
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse<AllUserDTO>> updateUserRole(@PathVariable Long id,
                                                                  @Valid @RequestBody RoleUpdateDTO roleUpdateDTO) {
        AllUserDTO updatedUser = allUserService.updateUserRole(id, roleUpdateDTO.getRole());

        ApiResponse<AllUserDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User role updated successfully",
                updatedUser
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AllUserDTO>> updateUserStatus(@PathVariable Long id,
                                                                    @Valid @RequestBody UserStatusDTO userStatusDTO) {
        AllUserDTO updatedUser = allUserService.updateUserStatus(id, userStatusDTO.getEnabled());

        ApiResponse<AllUserDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User status updated successfully",
                updatedUser
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ApiResponse<AllUserDTO>> changePassword(@PathVariable Long id,
                                                                  @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        AllUserDTO updatedUser = allUserService.changePassword(id, changePasswordDTO.getNewPassword());

        ApiResponse<AllUserDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User password updated successfully",
                updatedUser
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable Long id) {
        allUserService.deleteUser(id);

        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "User deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}