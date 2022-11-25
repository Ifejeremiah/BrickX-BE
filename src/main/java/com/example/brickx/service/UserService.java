package com.example.brickx.service;

import com.example.brickx.dtos.SignUpDto;
import com.example.brickx.dtos.UpdateDto;
import com.example.brickx.dtos.WorkerDto;
import com.example.brickx.entities.User;
import com.example.brickx.entities.Worker;

import java.util.List;

public interface UserService {
    void signUp(SignUpDto signUpDto);

    void updateUserProfile(Long id, UpdateDto updateDto);

    User getUser(Long id);

    List<Worker> allWorkersForProject(Long projectId);

    User findByEmail(String email);
}
