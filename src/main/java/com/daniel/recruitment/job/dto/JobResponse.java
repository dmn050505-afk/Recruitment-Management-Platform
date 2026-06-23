package com.daniel.recruitment.job.dto;

import com.daniel.recruitment.job.entity.JobStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class JobResponse {

    private UUID id;

    private String title;

    private String description;

    private String location;

    private BigDecimal salary;

    private JobStatus status;

    private LocalDateTime createdAt;

    private String recruiterEmail;

}