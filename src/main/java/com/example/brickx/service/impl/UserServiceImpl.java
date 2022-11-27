package com.example.brickx.service.impl;

import com.example.brickx.dtos.SignUpDto;
import com.example.brickx.dtos.UpdateDto;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.User;
import com.example.brickx.entities.Worker;
import com.example.brickx.entities.enums.JobType;
import com.example.brickx.entities.enums.Role;
import com.example.brickx.exceptions.BrickxAPIException;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.repository.WorkerRepository;
import com.example.brickx.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, WorkerRepository workerRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void signUp(SignUpDto signUpDto) {
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new BrickxAPIException(HttpStatus.BAD_REQUEST,"email does exist");
        } else {
            if(signUpDto.getUserType().equals(String.valueOf(Role.Contractor))){
                User contractor = modelMapper.map(signUpDto, Contractor.class);
                contractor.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
                contractor.setRole(Role.Contractor);
                userRepository.save(contractor);
            }
            if(signUpDto.getUserType().equals(String.valueOf(Role.Worker))){
                User worker = modelMapper.map(signUpDto, Worker.class);
                worker.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
                ((Worker) worker).setJobName(signUpDto.getJobType());
                worker.setRole(Role.Worker);
                userRepository.save(worker);
            }
        }
    }

    @Override
    public void updateUserProfile(Long id, UpdateDto updateDto) {
            User user = userRepository.findUserById(id);
            user.setFirstName(updateDto.getFirstName());
            user.setLastName(updateDto.getLastName());
            user.setGender(updateDto.getGender());
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<Worker> allWorkersForProject(Long projectId) {
        return workerRepository.findWorkersByProject_Id(projectId);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
