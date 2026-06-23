package com.daniel.recruitment.auth.service;

import com.daniel.recruitment.amodules.security.JwtService;
import com.daniel.recruitment.auth.dto.RegisterRequest;
import com.daniel.recruitment.amodules.exception.ResourceAlreadyExistsException;
import com.daniel.recruitment.user.dto.UserResponse;
import com.daniel.recruitment.user.entity.User;
import com.daniel.recruitment.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.daniel.recruitment.user.entity.Role;
import com.daniel.recruitment.auth.dto.AuthResponse;
import com.daniel.recruitment.auth.dto.LoginRequest;
import com.daniel.recruitment.amodules.exception.InvalidCredentialsException;
import com.daniel.recruitment.auth.dto.RecruiterRegisterRequest;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CANDIDATE)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid email or password"
                        ));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(
                user.getEmail()
        );

        return AuthResponse.builder()
                .token(token)
                .build();
    }


    @Override
    public UserResponse registerRecruiter(
            RecruiterRegisterRequest request
    ) {

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    "Email already exists"
            );
        }

        User recruiter = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.RECRUITER)
                .build();

        User savedRecruiter =
                userRepository.save(recruiter);

        return UserResponse.builder()
                .id(savedRecruiter.getId())
                .firstName(savedRecruiter.getFirstName())
                .lastName(savedRecruiter.getLastName())
                .email(savedRecruiter.getEmail())
                .role(savedRecruiter.getRole())
                .createdAt(savedRecruiter.getCreatedAt())
                .build();
    }
}