package com.inventoryfast.service.impl;

import com.inventoryfast.entity.User;
import com.inventoryfast.repository.UserRepository;
import com.inventoryfast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username ya existe");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
    
    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
