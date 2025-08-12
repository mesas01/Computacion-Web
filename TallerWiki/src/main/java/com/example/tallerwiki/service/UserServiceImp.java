package com.example.tallerwiki.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tallerwiki.model.User;
import com.example.tallerwiki.repository.UserRepository;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }
    
}
