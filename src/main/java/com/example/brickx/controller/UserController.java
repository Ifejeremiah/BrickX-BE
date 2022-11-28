package com.example.brickx.controller;


import com.example.brickx.dtos.UpdateDto;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.User;
import com.example.brickx.entities.Worker;
import com.example.brickx.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final String _PREFIX = "/worker";

    private final String ADMIN_PREFIX = "/contractor";

    @PatchMapping("worker/my-profile")
    public ResponseEntity<?> updateWorkerProfile(@AuthenticationPrincipal UserDetails currentUser, @RequestBody UpdateDto updateDto){
        userService.updateUserProfile(getCurrentUserId(currentUser),updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("contractor/my-profile")
    public ResponseEntity<?> updateContractorProfile(@AuthenticationPrincipal UserDetails currentUser, @RequestBody UpdateDto updateDto){
        userService.updateUserProfile(getCurrentUserId(currentUser),updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("contractor/my-profile")
    public ResponseEntity<Contractor> viewContractorProfile(@AuthenticationPrincipal UserDetails currentUser){
        return new ResponseEntity<>(userService.getContractor(getCurrentUserId(currentUser)),HttpStatus.OK);
    }

    @GetMapping("worker/my-profile")
    public ResponseEntity<Worker> viewWorkerProfile(@AuthenticationPrincipal UserDetails currentUser){
        return new ResponseEntity<>(userService.getWorker(getCurrentUserId(currentUser)),HttpStatus.OK);
    }


    private Long getCurrentUserId(@AuthenticationPrincipal UserDetails currentUser){
        User user = userService.findByEmail(currentUser.getUsername());
        return user.getId();
    }

}
