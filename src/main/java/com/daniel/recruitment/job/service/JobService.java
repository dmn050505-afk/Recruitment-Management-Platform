package com.daniel.recruitment.job.service;

import com.daniel.recruitment.job.dto.CreateJobRequest;
import com.daniel.recruitment.job.dto.JobResponse;
import java.util.List;

public interface JobService {

    JobResponse createJob(
            CreateJobRequest request
    );

    List<JobResponse> getAllJobs();

}