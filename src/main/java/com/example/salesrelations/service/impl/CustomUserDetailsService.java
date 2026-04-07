package com.example.salesrelations.service.impl;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.salesrelations.entity.AllUser;
import com.example.salesrelations.repository.AllUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AllUserRepository allUserRepository;

    public CustomUserDetailsService(AllUserRepository allUserRepository) {
        this.allUserRepository = allUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AllUser user = allUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole())
                )
        );
    }
}