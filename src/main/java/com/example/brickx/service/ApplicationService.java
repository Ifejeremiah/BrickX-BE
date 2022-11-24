package com.example.brickx.service;

import com.example.brickx.dtos.ApplicationDto;
import com.example.brickx.entities.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> allApplicationsToProject(Long projectId);

    Application viewApplication(Long projectId,Long applicationId,Long userId);

    void acceptApplicationToProject(Long projectId,Long applicationId,Long userId);

    void declineApplicationToProject(Long projectId,Long applicationId,Long userId);
}
