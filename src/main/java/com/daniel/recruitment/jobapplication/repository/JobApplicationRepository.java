package com.daniel.recruitment.jobapplication.repository;

import com.daniel.recruitment.jobapplication.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, UUID> {

    Optional<JobApplication>
    findByJobIdAndCandidateId(
            UUID jobId,
            UUID candidateId
    );

    List<JobApplication> findByJobRecruiterId(
            UUID recruiterId
    );
}