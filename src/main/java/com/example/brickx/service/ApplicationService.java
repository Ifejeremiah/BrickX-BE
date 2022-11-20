package com.example.brickx.service;

import com.example.brickx.entities.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> allApplicationsToProject(Long projectId);

    void acceptApplicationToProject(Application application);

    void declineApplicationToProject(Application application);
}
