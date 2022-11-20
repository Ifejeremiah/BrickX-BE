package com.example.brickx.service;

import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;

import java.util.List;

public interface WorkerService {

    void signUp(Worker worker);

    void updateProfile(Long id,Worker worker);

    Project viewProject();



}
