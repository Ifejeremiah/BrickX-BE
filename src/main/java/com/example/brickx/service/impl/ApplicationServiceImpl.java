package com.example.brickx.service.impl;

import com.example.brickx.dtos.ApplicationDto;
import com.example.brickx.entities.Application;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.entities.enums.ApplicationStatus;
import com.example.brickx.exceptions.ResourceNotFoundException;
import com.example.brickx.repository.ApplicationRepository;
import com.example.brickx.repository.ProjectRepository;
import com.example.brickx.repository.WorkerRepository;
import com.example.brickx.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;
    private final WorkerRepository workerRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ProjectRepository projectRepository, ModelMapper modelMapper, WorkerRepository workerRepository) {
        this.applicationRepository = applicationRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.workerRepository = workerRepository;
    }


    @Override
    public List<Application> allApplicationsToProject(Long projectId) {
        return applicationRepository.findApplicationsByProject_Id(projectId);
    }

    @Override
    public Application viewApplication(Long projectId, Long applicationId, Long userId) {
        return applicationRepository.findApplicationByIdAndProject_IdAndWorker_Id(applicationId,projectId,userId);
    }

    @Override
    public void acceptApplicationToProject(Long projectId, Long applicationId, Long userId) {
//        Project project = pro
    }

    @Override
    public void declineApplicationToProject(Long projectId, Long applicationId, Long userId) {

    }

//    @Override
//    public void acceptApplicationToProject(ApplicationDto applicationDto) {
//
//        Application application = new Application();
//
//        Project project = projectRepository.findById(Long.getLong(applicationDto.getProjectId())).orElseThrow(() -> new ResourceNotFoundException("project not found"));
//
//        Worker worker = workerRepository.findWorkerById(Long.getLong(applicationDto.getWorkerId())).orElseThrow(() -> new ResourceNotFoundException("worker not found"));
//
//        project.getWorker().add(worker);
//
//        projectRepository.save(project);
//
//        application.setProject(project);
//        application.setWorker(worker);
//        application.setStatus(ApplicationStatus.Accepted);
//
//        applicationRepository.save(application);
//    }
//
//    @Override
//    public void declineApplicationToProject(ApplicationDto applicationDto) {
//
//        Application application = new Application();
//
//        Project project = projectRepository.findById(Long.getLong(applicationDto.getProjectId())).orElseThrow(() -> new ResourceNotFoundException("project not found"));
//
//        Worker worker = workerRepository.findWorkerById(Long.getLong(applicationDto.getWorkerId())).orElseThrow(() -> new ResourceNotFoundException("worker not found"));
//
//        application.setProject(project);
//        application.setWorker(worker);
//        application.setStatus(ApplicationStatus.Rejected);
//        applicationRepository.save(application);
//    }
}
