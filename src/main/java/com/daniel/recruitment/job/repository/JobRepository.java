package com.daniel.recruitment.job.repository;

import com.daniel.recruitment.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository
        extends JpaRepository<Job, UUID> {
}