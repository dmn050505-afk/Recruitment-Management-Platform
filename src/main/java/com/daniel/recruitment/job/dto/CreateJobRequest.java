package com.daniel.recruitment.job.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateJobRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Salary must be greater than zero"
    )
    private BigDecimal salary;
}