package com.daniel.recruitment.jobapplication.dto;

import com.daniel.recruitment.jobapplication.entity.ApplicationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class JobApplicationResponse {

    private UUID id;

    private UUID jobId;

    private String candidateEmail;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}