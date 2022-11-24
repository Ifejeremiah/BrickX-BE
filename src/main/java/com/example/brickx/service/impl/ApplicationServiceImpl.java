package com.example.brickx.service.impl;

import com.example.brickx.entities.Application;
import com.example.brickx.entities.Job;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.entities.enums.ApplicationStatus;
import com.example.brickx.exceptions.BrickxAPIException;
import com.example.brickx.exceptions.ResourceNotFoundException;
import com.example.brickx.repository.ApplicationRepository;
import com.example.brickx.repository.JobRepository;
import com.example.brickx.repository.ProjectRepository;
import com.example.brickx.repository.WorkerRepository;
import com.example.brickx.service.ApplicationService;
import com.example.brickx.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final ProjectService projectService;
    private final WorkerRepository workerRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobRepository jobRepository, ProjectService projectService, WorkerRepository workerRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.projectService = projectService;
        this.workerRepository = workerRepository;
    }


    @Override
    public void createApplication(Long workerId, Long jobId) {
        Worker worker = findWorker(workerId);
        Job job = findJob(jobId);
        boolean applicationExist = job.getApplication().stream().anyMatch(application -> application.getWorker().getId().equals(workerId));
        if(applicationExist){
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST,"application created already");
        } else{
            Application application = new Application();
            application.setStatus(ApplicationStatus.Applied);
            application.setWorker(worker);
            application.setJob(job);
            applicationRepository.save(application);
        }
    }

    @Override
    public List<Application> allApplicationsToJob(Long jobId) {
        return findJob(jobId).getApplication();
    }

    @Override
    public List<Application> allApplicationsByWorker(Long workerId){
        return applicationRepository.findApplicationsByWorker_Id(workerId);
    }


    @Override
    public Application viewApplication(Long applicationId) {
        return applicationRepository.findApplicationById(applicationId);
    }

    @Override
    public List<Application> allApplicationsForProject(Long contractorId, Long projectId) {
        Project project1 = projectService.projectsByContractorId(contractorId).stream().filter(project -> project.getId().equals(projectId)).findAny().get();
        return project1.getJobs().stream().flatMap(job -> job.getApplication().parallelStream()).toList();
    }

    @Override
    public void acceptApplicationToJob(Long jobId, Long applicationId, Long workerId) {
        Application application = applicationRepository.findApplicationByIdAndJob_IdAndWorker_Id(applicationId,jobId,workerId);
     application.setStatus(ApplicationStatus.Accepted);
     applicationRepository.save(application);
     Job job = findJob(jobId);
     Worker worker = findWorker(workerId);
     job.getProject().getWorker().add(worker);
     jobRepository.save(job);
    }

    @Override
    public void declineApplicationToJob(Long jobId, Long applicationId, Long workerId) {
        Application application = applicationRepository.findApplicationByIdAndJob_IdAndWorker_Id(applicationId,jobId,workerId);
        application.setStatus(ApplicationStatus.Rejected);
        applicationRepository.save(application);
    }

    private Worker findWorker(Long id){
        return workerRepository.findWorkerById(id).orElseThrow(()-> new ResourceNotFoundException("worker not found"));
    }

    private Job findJob(Long id){
        return jobRepository.findJobById(id);
    }

}
