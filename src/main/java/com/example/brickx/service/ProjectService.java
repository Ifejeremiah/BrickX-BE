package com.example.brickx.service;

import com.example.brickx.dtos.ProjectDto;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.ProjectStatus;

import java.util.List;

public interface ProjectService {

    void createProject(ProjectDto projectDto);

    Project viewProject(Long id);

    List<Project> projectsByJobType(JobType jobType);

    List<Project> projectsByWorkerId(Long id);

    void updateProjectStatus(Long projectId, ProjectStatus projectStatus);
}
