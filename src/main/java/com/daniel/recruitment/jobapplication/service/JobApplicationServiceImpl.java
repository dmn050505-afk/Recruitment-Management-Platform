package com.daniel.recruitment.jobapplication.service;

import com.daniel.recruitment.amodules.exception.ResourceAlreadyExistsException;
import com.daniel.recruitment.amodules.exception.ResourceNotFoundException;
import com.daniel.recruitment.amodules.exception.UnauthorizedActionException;
import com.daniel.recruitment.job.entity.Job;
import com.daniel.recruitment.job.entity.JobStatus;
import com.daniel.recruitment.job.repository.JobRepository;
import com.daniel.recruitment.jobapplication.dto.JobApplicationResponse;
import com.daniel.recruitment.jobapplication.dto.RecruiterApplicationResponse;
import com.daniel.recruitment.jobapplication.entity.JobApplication;
import com.daniel.recruitment.jobapplication.repository.JobApplicationRepository;
import com.daniel.recruitment.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.daniel.recruitment.jobapplication.entity.ApplicationStatus;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl
        implements JobApplicationService {

    private final JobRepository jobRepository;

    private final JobApplicationRepository
            applicationRepository;

    @Override
    public JobApplicationResponse apply(
            UUID jobId
    ) {

        User candidate =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found"
                        ));

        if (job.getStatus() != JobStatus.OPEN) {
            throw new IllegalStateException(
                    "Job is closed"
            );
        }

        boolean alreadyApplied =
                applicationRepository
                        .findByJobIdAndCandidateId(
                                jobId,
                                candidate.getId()
                        )
                        .isPresent();

        if (alreadyApplied) {
            throw new ResourceAlreadyExistsException(
                    "Already applied to this job"
            );
        }

        JobApplication application =
                JobApplication.builder()
                        .job(job)
                        .candidate(candidate)
                        .build();

        JobApplication saved =
                applicationRepository
                        .save(application);

        return JobApplicationResponse.builder()
                .id(saved.getId())
                .jobId(job.getId())
                .candidateEmail(
                        candidate.getEmail()
                )
                .status(saved.getStatus())
                .appliedAt(saved.getAppliedAt())
                .build();
    }

    @Override
    public List<RecruiterApplicationResponse>
    getMyApplications() {

        User recruiter =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return applicationRepository
                .findByJobRecruiterId(
                        recruiter.getId()
                )
                .stream()
                .map(application ->
                        RecruiterApplicationResponse
                                .builder()
                                .applicationId(
                                        application.getId()
                                )
                                .jobId(
                                        application
                                                .getJob()
                                                .getId()
                                )
                                .jobTitle(
                                        application
                                                .getJob()
                                                .getTitle()
                                )
                                .candidateEmail(
                                        application
                                                .getCandidate()
                                                .getEmail()
                                )
                                .status(
                                        application.getStatus()
                                )
                                .appliedAt(
                                        application.getAppliedAt()
                                )
                                .build()
                )
                .toList();
    }

    @Override
    public RecruiterApplicationResponse
    updateStatus(
            UUID applicationId,
            ApplicationStatus status
    ) {

        User recruiter =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        JobApplication application =
                applicationRepository
                        .findById(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found"
                                ));

        if (!application.getJob()
                .getRecruiter()
                .getId()
                .equals(recruiter.getId())) {

            throw new UnauthorizedActionException(
                    "You do not own this job"
            );
        }

        application.setStatus(status);

        JobApplication updated =
                applicationRepository.save(
                        application
                );

        return RecruiterApplicationResponse
                .builder()
                .applicationId(updated.getId())
                .jobId(updated.getJob().getId())
                .jobTitle(updated.getJob().getTitle())
                .candidateEmail(
                        updated.getCandidate()
                                .getEmail()
                )
                .status(updated.getStatus())
                .appliedAt(updated.getAppliedAt())
                .build();
    }
}