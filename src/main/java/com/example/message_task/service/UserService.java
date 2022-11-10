package com.example.message_task.service;

import com.example.message_task.models.User;
import com.example.message_task.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User findByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByNameAndPassword(String username, String password) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the Database");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the Database: {}", username);
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
