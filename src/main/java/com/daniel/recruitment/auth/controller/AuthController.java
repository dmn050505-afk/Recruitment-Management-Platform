package com.daniel.recruitment.auth.controller;

import com.daniel.recruitment.auth.dto.RecruiterRegisterRequest;
import com.daniel.recruitment.auth.dto.RegisterRequest;
import com.daniel.recruitment.auth.service.AuthService;
import com.daniel.recruitment.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.daniel.recruitment.auth.dto.AuthResponse;
import com.daniel.recruitment.auth.dto.LoginRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }

    @PostMapping("/register-recruiter")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerRecruiter(
            @Valid @RequestBody RecruiterRegisterRequest request
    ) {
        return authService.registerRecruiter(request);
    }
}