package com.example.brickx.controller;

import com.example.brickx.dtos.UpdateDto;
import com.example.brickx.entities.Application;
import com.example.brickx.entities.Job;
import com.example.brickx.entities.User;
import com.example.brickx.entities.Worker;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.service.ApplicationService;
import com.example.brickx.service.JobService;
import com.example.brickx.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    private final UserRepository userRepository;
    private final JobService jobService;
    private final UserService userService;
    private final ApplicationService applicationService;

    public JobController(UserRepository userRepository, JobService jobService, UserService userService, ApplicationService applicationService) {
        this.userRepository = userRepository;
        this.jobService = jobService;
        this.userService = userService;
        this.applicationService = applicationService;
    }

    private final String _PREFIX = "/worker";

    private final String ADMIN_PREFIX = "/contractor";

    @RolesAllowed("Worker")
    @GetMapping("/worker/jobs")
    public ResponseEntity<List<Job>> allJobsByJobType(@AuthenticationPrincipal UserDetails currentUser){
        Worker worker = (Worker) userRepository.findUserByEmail(currentUser.getUsername());
        return ResponseEntity.ok(jobService.allJobsByJobName(worker.getJobName()));
    }


    @RolesAllowed("Worker")
    @PostMapping("/worker/jobs/{jid}")
    public ResponseEntity<String> applyForJob(@AuthenticationPrincipal UserDetails currentUser, @PathVariable(name = "jid") Long idj){
        applicationService.createApplication(getCurrentUserId(currentUser), idj);
        return ResponseEntity.ok("applied");
    }

    @RolesAllowed("Worker")
    @GetMapping("/workers/{wid}/jobs/{jid}/requests")
    public ResponseEntity<List<Application>> allApplicationsToJob(@PathVariable(name = "jid") Long idj){
        return ResponseEntity.ok(applicationService.allApplicationsToJob(idj));
    }



    @PostMapping("/contractor/jobs/{jid}/requests/{rid}/accept")
    public ResponseEntity<?> acceptApplicationToJob(@AuthenticationPrincipal UserDetails currentUser, @PathVariable(name = "jid") Long idj, @PathVariable(name = "rid")Long idr){
        applicationService.acceptApplicationToJob(getCurrentUserId(currentUser),idj,idr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/contractor/jobs/{jid}/requests/{rid}/decline")
    public ResponseEntity<?> rejectApplicationToJob(@AuthenticationPrincipal UserDetails currentUser, @PathVariable(name = "jid") Long idj, @PathVariable(name = "rid")Long idr){
        applicationService.declineApplicationToJob(getCurrentUserId(currentUser),idj,idr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long getCurrentUserId(@AuthenticationPrincipal UserDetails currentUser){
        User user = userService.findByEmail(currentUser.getUsername());
        return user.getId();
    }

}
