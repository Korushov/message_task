package com.example.message_task.controllers;

import com.example.message_task.models.User;
import com.example.message_task.security.jwt.JwtProvider;
import com.example.message_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    private final JwtProvider jwtProvider;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userService.findByNameAndPassword(request.getName(), request.getPassword());
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
