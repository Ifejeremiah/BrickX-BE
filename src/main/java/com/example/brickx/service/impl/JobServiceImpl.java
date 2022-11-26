package com.example.brickx.service.impl;

import com.example.brickx.entities.Job;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.repository.JobRepository;
import com.example.brickx.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Job> allJobsByJobName(String name) {
        return jobRepository.findJobsByName(name);
    }
}
