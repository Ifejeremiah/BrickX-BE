package com.example.brickx.service.impl;

import com.example.brickx.dtos.ProjectDto;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.ProjectStatus;
import com.example.brickx.exceptions.ResourceNotFoundException;
import com.example.brickx.repository.ContractorRepository;
import com.example.brickx.repository.ProjectRepository;
import com.example.brickx.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ContractorRepository contractorRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ContractorRepository contractorRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.contractorRepository = contractorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createProject(Long contractorId,ProjectDto projectDto) {
        boolean projectDoesExist = projectRepository.existsProjectByContractor_IdAndTitle(contractorId, projectDto.getTitle());
        if(projectDoesExist){
            throw new ResourceNotFoundException("project does exist");
        } else {
            Project project = modelMapper.map(projectDto, Project.class);
            project.setContractor(contractorRepository.findContractorById(contractorId));
            project.setProjectStatus(ProjectStatus.Open);
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

    @Override
    public List<Project> projectsByWorkerId(Long id) {
        return projectRepository.findAll().stream().filter(project -> project.getWorker().stream().filter(worker -> Objects.equals(worker.getId(), id)).isParallel()).toList();
    }

    @Override
    public void updateProjectStatus(Long id,ProjectStatus projectStatus) {
        Project project = viewProject(id);
        project.setProjectStatus(projectStatus);
        projectRepository.save(project);
    }
}
