package com.example.brickx.controller;

import com.example.brickx.entities.Application;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.Worker;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    private final String _PREFIX = "/worker";

    private final String ADMIN_PREFIX = "/contractor";

    @RolesAllowed("Worker")
    @GetMapping("workers/{wid}/requests")
    public ResponseEntity<List<Application>> allRequestsByWorkerId(@AuthenticationPrincipal UserDetails currentUser){
        Worker worker = (Worker) userRepository.findUserByEmail(currentUser.getUsername());
        return ResponseEntity.ok(applicationService.allApplicationsByWorker(worker.getId()));
    }


    @RolesAllowed({"Worker"})
    @GetMapping("workers/{wid}/requests/{rid}")
    public ResponseEntity<Application> getApplicationDetailsAsWorker(@PathVariable(name = "rid") Long id){
        return ResponseEntity.ok(applicationService.viewApplication(id));
    }

    @RolesAllowed({"Contractor"})
    @GetMapping("contractors/{cid}/requests/{rid}")
    public ResponseEntity<Application> getApplicationDetailsAsContractor(@PathVariable(name = "rid") Long id){
        return ResponseEntity.ok(applicationService.viewApplication(id));
    }



}
