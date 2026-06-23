package com.daniel.recruitment.job.service;

import com.daniel.recruitment.job.dto.CreateJobRequest;
import com.daniel.recruitment.job.dto.JobResponse;
import com.daniel.recruitment.job.entity.Job;
import com.daniel.recruitment.job.repository.JobRepository;
import com.daniel.recruitment.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public JobResponse createJob(
            CreateJobRequest request
    ) {

        User recruiter =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .salary(request.getSalary())
                .recruiter(recruiter)
                .build();

        Job savedJob =
                jobRepository.save(job);

        return JobResponse.builder()
                .id(savedJob.getId())
                .title(savedJob.getTitle())
                .description(savedJob.getDescription())
                .location(savedJob.getLocation())
                .salary(savedJob.getSalary())
                .status(savedJob.getStatus())
                .createdAt(savedJob.getCreatedAt())
                .recruiterEmail(
                        recruiter.getEmail()
                )
                .build();
    }

    @Override
    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(job -> JobResponse.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .description(job.getDescription())
                        .location(job.getLocation())
                        .salary(job.getSalary())
                        .status(job.getStatus())
                        .createdAt(job.getCreatedAt())
                        .recruiterEmail(
                                job.getRecruiter().getEmail()
                        )
                        .build())
                .toList();
    }
}