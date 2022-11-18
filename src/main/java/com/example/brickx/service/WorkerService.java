package com.example.brickx.service;

import com.example.brickx.entities.Worker;

import java.util.List;

public interface WorkerService {
    Worker saveWorker(Worker worker);

    List<Worker> fetchWorkerList();
}
