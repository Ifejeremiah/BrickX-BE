package com.example.brickx.controller;

import com.example.brickx.dtos.JWTAuthResponse;
import com.example.brickx.dtos.LoginDto;
import com.example.brickx.dtos.SignUpDto;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.User;
import com.example.brickx.entities.Worker;
import com.example.brickx.security.JwtTokenProvider;
import com.example.brickx.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        boolean worker = authentication.getAuthorities().toString().equals("Worker");

        boolean Contractor = authentication.getAuthorities().toString().equals("Contractor");

        if (worker) {
            Worker user = (Worker) userService.findByEmail(loginDto.getEmail());
            return ResponseEntity.ok(new JWTAuthResponse(token, user.getId()));
        } else {
            Contractor user = (com.example.brickx.entities.Contractor) userService.findByEmail(loginDto.getEmail());
            return ResponseEntity.ok(new JWTAuthResponse(token, user.getId()));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
