package com.andrein.teamtackle.service;

import com.andrein.teamtackle.entity.User;
import com.andrein.teamtackle.exceptions.UserEmailAlreadyPresentException;
import com.andrein.teamtackle.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (isEmailAlreadyInUse(user.getEmail())) {
            throw new UserEmailAlreadyPresentException("Email already in use");
        }
        return userRepository.save(user);
    }

    private boolean isEmailAlreadyInUse(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
