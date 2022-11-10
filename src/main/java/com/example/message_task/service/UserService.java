package com.example.message_task.service;

import com.example.message_task.models.User;
import com.example.message_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User findByLogin(String username){
        return userRepository.findByUsername(username);
    }


    public User findByNameAndPassword(String username, String password) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        // Invalid Credentials
        if (user == null) {
            log.error("User not found in the Database");
        } else {
            log.info("User found in the Database: {}", username);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found");

    }
}
