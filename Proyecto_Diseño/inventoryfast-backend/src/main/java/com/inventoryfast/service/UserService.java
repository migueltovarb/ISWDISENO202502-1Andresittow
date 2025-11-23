package com.inventoryfast.service;

import com.inventoryfast.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(String id, User user);
    void deleteUser(String id);
    Optional<User> getUserById(String id);
    List<User> getAllUsers();
    Optional<User> getUserByUsername(String username);
}