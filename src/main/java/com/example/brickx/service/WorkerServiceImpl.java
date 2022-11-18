package com.example.brickx.service;

import com.example.brickx.entities.Worker;
import com.example.brickx.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    //    @Autowired
    private final WorkerRepository workerRepository;

    @Override
    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public List<Worker> fetchWorkerList() {
        return workerRepository.findAll();
    }
}
