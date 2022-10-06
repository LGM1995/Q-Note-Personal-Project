package com.example.qnote.config.service;

import com.example.qnote.model.User;
import com.example.qnote.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User save(User user) {
        return userRepository.save(user);
    }
}
