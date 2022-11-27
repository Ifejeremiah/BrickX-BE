package com.example.brickx.service.impl;

import com.example.brickx.dtos.ProjectDto;
import com.example.brickx.entities.Job;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.enums.ProjectStatus;
import com.example.brickx.exceptions.ResourceNotFoundException;
import com.example.brickx.repository.ContractorRepository;
import com.example.brickx.repository.JobRepository;
import com.example.brickx.repository.ProjectRepository;
import com.example.brickx.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ContractorRepository contractorRepository;

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ContractorRepository contractorRepository, JobRepository jobRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.contractorRepository = contractorRepository;
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createProject(Long contractorId,ProjectDto projectDto) {
        boolean projectDoesExist = projectRepository.existsProjectByContractor_IdAndTitle(contractorId, projectDto.getTitle());
        if(projectDoesExist){
            throw new ResourceNotFoundException("project does exist");
        } else {
            Project project = new Project();
            project.setTitle(projectDto.getTitle());
            project.setDuration(projectDto.getDuration());
            project.setBudget(projectDto.getBudget());
            project.setDateCreated(new Date());
            project.setStartDate(projectDto.getStartDate());
            project.setContractor(contractorRepository.findContractorById(contractorId));
            project.setProjectStatus(ProjectStatus.Open);
            List<Job> jobs = projectDto.getJobs().stream().map(jobName-> jobRepository.findJobByName(jobName)).toList();
            project.setJobs(jobs);
            projectRepository.save(project);
        }
    }

    @Override
    public Project viewProject(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public List<Project> projectsByContractorId(Long contractorId) {
        return projectRepository.findProjectsByContractor_Id(contractorId);
    }

//    @Override
//    public List<Project> projectsByJobType(JobType jobType) {
//        List<Project> projects = projectRepository.findAll();
//        return projects.stream().filter(project -> project.getJobs().stream().filter(job -> job.getJobType() == jobType).isParallel()).toList();
//    }

//    @Override
//    public List<Project> projectsByWorkerId(Long id) {
//        return projectRepository.findAll().stream().filter(project -> project.getWorker().stream().filter(worker -> Objects.equals(worker.getId(), id)).isParallel()).toList();
//    }

    @Override
    public void updateProjectStatus(Long id,String projectStatus) {
        Project project = viewProject(id);
        project.setProjectStatus(ProjectStatus.valueOf(projectStatus));
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Long projectId, ProjectDto projectDto) {
        Project project = viewProject(projectId);
        project.setBudget(projectDto.getBudget());
        project.setTitle(projectDto.getTitle());
        project.setDuration(projectDto.getDuration());
        projectRepository.save(project);
    }
}
