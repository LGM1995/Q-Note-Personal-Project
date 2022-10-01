package com.example.security.config.service;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User save(User user) {
        return userRepository.save(user);
    }
}
