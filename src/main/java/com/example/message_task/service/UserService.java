package com.example.message_task.service;

import com.example.message_task.models.User;
import com.example.message_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        if (user == null) {
            log.error("User not found in the Database");
        } else {
            log.info("User found in the Database: {}", username);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    public List<String> findAllUsernames() {
        List<User> users = userRepository.findAll();
        List<String> usernames = new ArrayList<>();
        for (User user : users) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    @Transactional(readOnly = false)
    public void addNewUser(User user) {
        userRepository.save(user);
    }
}
