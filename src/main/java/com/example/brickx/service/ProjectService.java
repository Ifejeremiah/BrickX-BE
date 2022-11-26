package com.example.brickx.service;

import com.example.brickx.dtos.ProjectDto;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.ProjectStatus;

import java.util.List;

public interface ProjectService {

    void createProject(Long contractorId,ProjectDto projectDto);

    Project viewProject(Long id);

    List<Project> projectsByContractorId(Long contractorId);


  //  List<Project> projectsByWorkerId(Long workerId);

    void updateProjectStatus(Long projectId, String projectStatus);

    void updateProject(Long projectId, ProjectDto projectDto);
}
