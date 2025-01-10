package com.courtside.api.service;

import com.courtside.api.dto.AuthenticationRequest;
import com.courtside.api.dto.AuthenticationResponse;
import com.courtside.api.dto.RegisterRequest;
import com.courtside.api.entity.User;
import com.courtside.api.exception.InvalidPasswordException;
import com.courtside.api.exception.RegistrationFailedException;
import com.courtside.api.exception.UserAlreadyExistsException;
import com.courtside.api.repository.UserRepository;
import com.courtside.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        // Validate password requirements (example)
        if (request.getPassword().length() < 8) {
            throw new InvalidPasswordException("Password must be at least 6 characters");
        }

        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RegistrationFailedException("Failed to register user");
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}