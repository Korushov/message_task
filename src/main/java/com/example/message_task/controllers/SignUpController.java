package com.example.message_task.controllers;

import com.example.message_task.dto.SignRequest;
import com.example.message_task.models.User;
import com.example.message_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody SignRequest signRequest) {
        List<String> usernames = userService.findAllUsernames();
        String username = signRequest.getName();
        for (String name : usernames) {
            if (name.equals(username)) {
                throw new BadCredentialsException("Username is already taken");
            }
        }

        userService.addNewUser(new User(signRequest.getName(), signRequest.getPassword()));
        return HttpStatus.OK;
    }
}
