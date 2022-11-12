package com.example.message_task.service;

import com.example.message_task.models.User;
import com.example.message_task.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByNameAndPassword() {
        User user = new User("test", "testpass");
        Mockito.lenient().when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User test = userService.findByLogin(user.getUsername());

        Assert.assertEquals(test.getUsername(), user.getUsername());
        Assert.assertEquals(test.getPassword(), user.getPassword());
    }

    @Test
    public void nullUserTest() {
        User user = new User();
        Mockito.lenient().when(userService.findByLogin(user.getUsername())).thenReturn(null);
        User test = userService.findByLogin(user.getUsername());

        Assert.assertNull(test);
    }

    @Test
    public void badCredentialsTest() {
        User user = new User("name", "password");
        Mockito.lenient().when(userRepository.findByUsername(user.getUsername())).thenReturn(new User("name", "testpass"));

        try {
            User test = userService.findByNameAndPassword(user.getUsername(), user.getPassword());
            Assert.assertNull(test);
        } catch (BadCredentialsException e) {
            Assert.assertEquals("Bad Credentials", e.getMessage());
        }
    }
}
