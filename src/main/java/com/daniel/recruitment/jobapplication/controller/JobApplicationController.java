package com.daniel.recruitment.jobapplication.controller;

import com.daniel.recruitment.jobapplication.dto.JobApplicationResponse;
import com.daniel.recruitment.jobapplication.dto.RecruiterApplicationResponse;
import com.daniel.recruitment.jobapplication.dto.UpdateApplicationStatusRequest;
import com.daniel.recruitment.jobapplication.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobApplicationController {

    private final JobApplicationService
            jobApplicationService;

    @PostMapping("/{jobId}/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    public JobApplicationResponse apply(
            @PathVariable UUID jobId
    ) {
        return jobApplicationService.apply(jobId);
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('RECRUITER')")
    public List<RecruiterApplicationResponse>
    getMyApplications() {

        return jobApplicationService
                .getMyApplications();
    }

    @PatchMapping(
            "/applications/{applicationId}/status"
    )
    @PreAuthorize("hasRole('RECRUITER')")
    public RecruiterApplicationResponse
    updateStatus(
            @PathVariable UUID applicationId,
            @Valid @RequestBody
            UpdateApplicationStatusRequest request
    ) {

        return jobApplicationService
                .updateStatus(
                        applicationId,
                        request.getStatus()
                );
    }
}