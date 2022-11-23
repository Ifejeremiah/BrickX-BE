package com.example.brickx.service;

import com.example.brickx.dtos.SignUpDto;
import com.example.brickx.dtos.UpdateDto;
import com.example.brickx.dtos.WorkerDto;

public interface UserService {
    void signUp(SignUpDto signUpDto);

    void updateUserProfile(Long id, UpdateDto updateDto);
}
