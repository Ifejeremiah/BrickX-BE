package com.example.brickx.controller;

import com.example.brickx.entities.Worker;
import com.example.brickx.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping("/workers")
    public Worker saveWorker(@RequestBody Worker worker) {
        return workerService.saveWorker(worker);
    }

    @GetMapping("/workers")
    public List<Worker> fetchWorkerList() {
        return workerService.fetchWorkerList();
    }
}
