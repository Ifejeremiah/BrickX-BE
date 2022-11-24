package com.example.brickx.controller;

import com.example.brickx.entities.Application;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.service.ApplicationService;
import com.example.brickx.service.ProjectService;
import com.example.brickx.service.UserService;
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
public class ProjectController {

    private final ProjectService projectService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ApplicationService applicationService;

    public ProjectController(ProjectService projectService, UserRepository userRepository, UserService userService, ApplicationService applicationService) {
        this.projectService = projectService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.applicationService = applicationService;
    }



    @RolesAllowed("Contractor")
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String email = userDetails.getUsername();
        Contractor contractor = (Contractor) userRepository.findUserByEmail(email);
        return ResponseEntity.ok(projectService.projectsByContractorId(contractor.getId()));
    }

    @RolesAllowed("Contractor")
    @GetMapping("/projects/{pid}/applications")
    public ResponseEntity<List<Application>> getAllApplicationsForProject(@PathVariable(name = "pid")Long idp){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String email = userDetails.getUsername();
        Contractor contractor = (Contractor) userRepository.findUserByEmail(email);
        return ResponseEntity.ok(applicationService.allApplicationsForProject(contractor.getId(), idp));
    }

    @RolesAllowed("Contractor")
    @GetMapping("/projects/{pid}/workers")
    public ResponseEntity<List<Worker>> getAllWorkersForProject(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String email = userDetails.getUsername();
        Contractor contractor = (Contractor) userRepository.findUserByEmail(email);
        return ResponseEntity.ok(userService.allWorkersForProject(contractor.getId()));
    }
}
