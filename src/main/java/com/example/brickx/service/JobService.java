package com.example.brickx.service;

import com.example.brickx.entities.Job;
import com.example.brickx.entities.enums.JobType;

import java.util.List;

public interface JobService {

    List<Job> allJobsByJobType(JobType jobType);
}
