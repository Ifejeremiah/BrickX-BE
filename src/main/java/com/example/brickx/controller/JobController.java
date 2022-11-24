package com.example.brickx.controller;

import com.example.brickx.entities.Job;
import com.example.brickx.entities.Worker;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.service.ApplicationService;
import com.example.brickx.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    private final UserRepository userRepository;
    private final JobService jobService;
    private final ApplicationService applicationService;

    public JobController(UserRepository userRepository, JobService jobService, ApplicationService applicationService) {
        this.userRepository = userRepository;
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    @RolesAllowed("Worker")
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> allJobsByJobType(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String email = userDetails.getUsername();
        Worker worker = (Worker) userRepository.findUserByEmail(email);
        return ResponseEntity.ok(jobService.allJobsByJobType(worker.getJobType()));
    }


    @RolesAllowed("Worker")
    @PostMapping("/jobs/{jid}")
    public ResponseEntity<String> applyForJob(@PathVariable(name = "wid") Long idw, @PathVariable(name = "jid") Long idj){

        applicationService.createApplication(idw,idj);
        return ResponseEntity.ok("applied");
    }

}
