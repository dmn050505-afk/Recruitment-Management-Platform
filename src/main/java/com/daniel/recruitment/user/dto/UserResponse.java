package com.daniel.recruitment.user.dto;

import com.daniel.recruitment.user.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private LocalDateTime createdAt;
}