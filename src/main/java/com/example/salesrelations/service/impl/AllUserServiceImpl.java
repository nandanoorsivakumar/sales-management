package com.example.salesrelations.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.salesrelations.dto.AllUserDTO;
import com.example.salesrelations.entity.AllUser;
import com.example.salesrelations.exception.BadRequestException;
import com.example.salesrelations.exception.DuplicateResourceException;
import com.example.salesrelations.exception.ResourceNotFoundException;
import com.example.salesrelations.repository.AllUserRepository;
import com.example.salesrelations.service.AllUserService;

@Service
public class AllUserServiceImpl implements AllUserService {

    private final AllUserRepository allUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AllUserServiceImpl(AllUserRepository allUserRepository,
                              PasswordEncoder passwordEncoder) {
        this.allUserRepository = allUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AllUserDTO createUser(AllUserDTO allUserDTO) {
        if (allUserRepository.existsByUsername(allUserDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists: " + allUserDTO.getUsername());
        }

        String role = allUserDTO.getRole().toUpperCase();
        if (!role.equals("ADMIN") && !role.equals("USER")) {
            throw new BadRequestException("Role must be ADMIN or USER");
        }

        AllUser user = new AllUser();
        user.setUsername(allUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(allUserDTO.getPassword()));
        user.setRole(role);
        user.setEnabled(allUserDTO.getEnabled());
        user.setEmployeeId(allUserDTO.getEmployeeId());

        AllUser savedUser = allUserRepository.save(user);
        return mapToDTO(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AllUserDTO updateUserRole(Long id, String role) {
        AllUser existingUser = allUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        String updatedRole = role.toUpperCase();
        if (!updatedRole.equals("ADMIN") && !updatedRole.equals("USER")) {
            throw new BadRequestException("Role must be ADMIN or USER");
        }

        existingUser.setRole(updatedRole);
        AllUser savedUser = allUserRepository.save(existingUser);

        return mapToDTO(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AllUserDTO updateUserStatus(Long id, Boolean enabled) {
        AllUser existingUser = allUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setEnabled(enabled);
        AllUser savedUser = allUserRepository.save(existingUser);

        return mapToDTO(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AllUserDTO changePassword(Long id, String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new BadRequestException("New password must not be empty");
        }

        AllUser existingUser = allUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setPassword(passwordEncoder.encode(newPassword));
        AllUser savedUser = allUserRepository.save(existingUser);

        return mapToDTO(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        AllUser existingUser = allUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        allUserRepository.delete(existingUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<AllUserDTO> getAllUsers() {
        return allUserRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AllUserDTO getUserById(Long id) {
        AllUser user = allUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return mapToDTO(user);
    }

    private AllUserDTO mapToDTO(AllUser user) {
        return new AllUserDTO(
                user.getId(),
                user.getUsername(),
                null,
                user.getRole(),
                user.getEnabled(),
                user.getEmployeeId()
        );
    }
}