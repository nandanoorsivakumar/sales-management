package com.example.salesrelations.service;

import java.util.List;

import com.example.salesrelations.dto.AllUserDTO;

public interface AllUserService {

    AllUserDTO createUser(AllUserDTO allUserDTO);

    AllUserDTO updateUserRole(Long id, String role);

    AllUserDTO updateUserStatus(Long id, Boolean enabled);

    AllUserDTO changePassword(Long id, String newPassword);

    void deleteUser(Long id);

    List<AllUserDTO> getAllUsers();

    AllUserDTO getUserById(Long id);
}