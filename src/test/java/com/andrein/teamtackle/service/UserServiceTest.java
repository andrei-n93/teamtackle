package com.andrein.teamtackle.service;

import com.andrein.teamtackle.entity.User;
import com.andrein.teamtackle.exceptions.UserEmailAlreadyPresentException;
import com.andrein.teamtackle.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUserSuccess() {
        User testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("testuser@something.com");
        testUser.setPassword("password1234");
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User savedUser = userService.createUser(testUser);

        verify(userRepository).save(testUser);
        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals(testUser.getEmail(), savedUser.getEmail(), "Emails should match");
    }

    @Test
    public void testDuplicateEmailUserCreationFails() {
        User testUser = new User();
        testUser.setUsername("existingUser");
        testUser.setEmail("existingemail@gmail.com");
        testUser.setPassword("password1234");
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        assertThrows(UserEmailAlreadyPresentException.class, () -> userService.createUser(testUser));

        verify(userRepository, never()).save(any(User.class));
    }
}