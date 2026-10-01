package com.devtestbuild.employeemanagement.service;

import com.devtestbuild.employeemanagement.entity.User;
import com.devtestbuild.employeemanagement.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException(
                    "Username already exists"
            );
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already exists"
            );
        }

        // Encrypt password before saving
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // Default role
        user.setRole("USER");

        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {

        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(
                        password,
                        user.getPassword()
                ))
                .orElse(false);
    }


}