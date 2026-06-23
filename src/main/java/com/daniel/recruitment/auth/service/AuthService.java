package com.daniel.recruitment.auth.service;

import com.daniel.recruitment.auth.dto.AuthResponse;
import com.daniel.recruitment.auth.dto.LoginRequest;
import com.daniel.recruitment.auth.dto.RegisterRequest;
import com.daniel.recruitment.user.dto.UserResponse;
import com.daniel.recruitment.auth.dto.RecruiterRegisterRequest;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    UserResponse registerRecruiter(
            RecruiterRegisterRequest request
    );
}