package com.example.brickx.controller;

import com.example.brickx.dtos.ProjectDto;
import com.example.brickx.entities.Application;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.Project;
import com.example.brickx.entities.Worker;
import com.example.brickx.repository.UserRepository;
import com.example.brickx.service.ApplicationService;
import com.example.brickx.service.ProjectService;
import com.example.brickx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Slf4j
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
    @PostMapping("/projects")
    public ResponseEntity<String> createProject(@RequestBody ProjectDto projectDto, @AuthenticationPrincipal UserDetails currentUser){
        System.out.println(projectDto);
        Contractor contractor = (Contractor) userRepository.findUserByEmail(currentUser.getUsername());
        projectService.createProject(contractor.getId(), projectDto);
        return ResponseEntity.ok("created");
    }

    @RolesAllowed("Contractor")
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(@AuthenticationPrincipal UserDetails currentUser) {
        Contractor contractor = (Contractor) userRepository.findUserByEmail(currentUser.getUsername());
        return ResponseEntity.ok(projectService.projectsByContractorId(contractor.getId()));
    }

    @RolesAllowed("Contractor")
    @PatchMapping("/projects/{pid}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDto projectDto,@PathVariable(name = "pid")Long id) {
        projectService.updateProject(id,projectDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed("Contractor")
    @PostMapping("/projects/{pid}")
    public ResponseEntity<?> updateProjectStatus(@PathVariable(name = "pid")Long id, @RequestBody ProjectDto projectDto) {
        projectService.updateProjectStatus(id, projectDto.getStatus());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RolesAllowed("Contractor")
    @GetMapping("/projects/{pid}/applications")
    public ResponseEntity<List<Application>> getAllApplicationsForProject(@PathVariable(name = "pid") Long idp,@AuthenticationPrincipal UserDetails currentUser) {
        Contractor contractor = (Contractor) userRepository.findUserByEmail(currentUser.getUsername());
        return ResponseEntity.ok(applicationService.allApplicationsForProject(contractor.getId(), idp));
    }

    @RolesAllowed("Contractor")
    @GetMapping("/projects/{pid}/workers")
    public ResponseEntity<List<Worker>> getAllWorkersForProject(@AuthenticationPrincipal UserDetails currentUser) {
        Contractor contractor = (Contractor) userRepository.findUserByEmail(currentUser.getUsername());
        return ResponseEntity.ok(userService.allWorkersForProject(contractor.getId()));
    }
}
