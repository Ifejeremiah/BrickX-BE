package com.example.brickx.service.impl;

import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.exceptions.ResourceNotFoundException;
import com.example.brickx.repository.WorkerRepository;
import com.example.brickx.service.WorkerService;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {

    //    @Autowired
    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }


    @Override
    public void signUp(Worker worker) {
        if (workerRepository.existWorkerByEmail(worker.getEmail())) {
            throw new ResourceNotFoundException("worker email does exist");
        } else {
            Worker worker1 = new Worker();

        }


    }

    @Override
    public void updateProfile(Long id, Worker worker) {

    }

    @Override
    public Project viewProject() {
        return null;
    }
}
