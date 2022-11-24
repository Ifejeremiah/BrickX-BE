package com.example.brickx.service;

import com.example.brickx.entities.Application;

import java.util.List;

public interface ApplicationService {

    void createApplication(Long workerId,Long jobId);

    List<Application> allApplicationsToJob(Long jobId);

    List<Application> allApplicationsByWorker(Long workerId);

    Application viewApplication(Long applicationId);

    List<Application> allApplicationsForProject(Long contractorId,Long projectId);

    void acceptApplicationToJob(Long workerId,Long jobId,Long applicationId);

    void declineApplicationToJob(Long workerId,Long jobId,Long applicationId);
}
