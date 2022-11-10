package com.example.message_task.controllers;

import com.example.message_task.models.User;
import com.example.message_task.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public HttpEntity<HttpStatus> auth(@RequestBody AuthRequest authRequest) {
        User user = userService.findByNameAndPassword(authRequest.getName(), authRequest.getPassword());
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
