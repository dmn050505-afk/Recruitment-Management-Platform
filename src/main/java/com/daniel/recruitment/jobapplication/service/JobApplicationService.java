package com.daniel.recruitment.jobapplication.service;

import com.daniel.recruitment.jobapplication.dto.JobApplicationResponse;
import com.daniel.recruitment.jobapplication.dto.RecruiterApplicationResponse;
import com.daniel.recruitment.jobapplication.entity.ApplicationStatus;

import java.util.List;
import java.util.UUID;

public interface JobApplicationService {

    JobApplicationResponse apply(
            UUID jobId
    );

    List<RecruiterApplicationResponse>
    getMyApplications();

    RecruiterApplicationResponse
    updateStatus(
            UUID applicationId,
            ApplicationStatus status
    );
}