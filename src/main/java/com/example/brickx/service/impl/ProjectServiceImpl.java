package com.example.brickx.service.impl;

import com.example.brickx.dtos.ProjectDto;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.ProjectStatus;
import com.example.brickx.exceptions.ResourceNotFoundException;
import com.example.brickx.repository.ProjectRepository;
import com.example.brickx.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createProject(ProjectDto projectDto) {
        boolean projectDoesExist = projectRepository.findAll().stream().anyMatch(project -> project.getTitle().equals(projectDto.getTitle()));
        String title = projectDto.getTitle();
        if(projectDoesExist){
            throw new ResourceNotFoundException("project does exist");
        } else {
            Project project = modelMapper.map(projectDto, Project.class);
            project.setProjectStatus(ProjectStatus.Open);
            projectRepository.save(project);
        }
    }

    @Override
    public Project viewProject(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public List<Project> projectsByJobType(JobType jobType) {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().filter(project -> project.getJobs().stream().filter(job -> job.getJobType() == jobType).isParallel()).toList();
    }

    @Override
    public List<Project> projectsByWorkerId(Long id) {
        return projectRepository.findAll().stream().filter(project -> project.getWorker().stream().filter(worker -> Objects.equals(worker.getId(), id)).isParallel()).toList();
    }

    @Override
    public void updateProjectStatus(Long projectId,ProjectStatus projectStatus) {
        projectRepository.findProjectById(projectId).setProjectStatus(projectStatus);
    }
}
