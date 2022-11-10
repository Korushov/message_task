package com.example.message_task.service;

import com.example.message_task.models.User;
import com.example.message_task.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin(String username) {
        return userRepository.findByUsername(username);
    }


}
