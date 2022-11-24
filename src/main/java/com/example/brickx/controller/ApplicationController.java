package com.example.brickx.controller;

import com.example.brickx.entities.Application;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.Worker;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final UserRepository userRepository;

    public ApplicationController(ApplicationService applicationService, UserRepository userRepository) {
        this.applicationService = applicationService;
        this.userRepository = userRepository;
    }



    @RolesAllowed("Worker")
    @GetMapping("/requests")
    public ResponseEntity<List<Application>> allRequestByWorkerId(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String email = userDetails.getUsername();
        Worker worker = (Worker) userRepository.findUserByEmail(email);
        return ResponseEntity.ok(applicationService.allApplicationsByWorker(worker.getId()));
    }

    @RolesAllowed({"Contractor","Worker"})
    @GetMapping("/requests/{rid}")
    public ResponseEntity<Application> getApplicationDetails(@PathVariable(name = "rid") Long id){
        return ResponseEntity.ok(applicationService.viewApplication(id));
    }

}
