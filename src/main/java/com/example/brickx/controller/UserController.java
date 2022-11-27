package com.example.brickx.controller;


import com.example.brickx.dtos.UpdateDto;
import com.example.brickx.entities.User;
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


    @PatchMapping("/my-profile")
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal UserDetails currentUser, @RequestBody UpdateDto updateDto){
        userService.updateUserProfile(getCurrentUserId(currentUser),updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<User> viewProfile(@AuthenticationPrincipal UserDetails currentUser){
        return new ResponseEntity<>(userService.getUser(getCurrentUserId(currentUser)),HttpStatus.OK);
    }


    private Long getCurrentUserId(@AuthenticationPrincipal UserDetails currentUser){
        User user = userService.findByEmail(currentUser.getUsername());
        return user.getId();
    }

}
