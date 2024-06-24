package com.dauphine.blogger.services;

import com.dauphine.blogger.models.User;
import com.dauphine.blogger.services.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User registerUser(String username, String password);
    User getUserById(UUID userId) throws UserNotFoundException;
    User getUserByUsername(String username) throws UserNotFoundException;
    List<User> getAllUsers();
    User updateUser(UUID userId, String username, String password) throws UserNotFoundException;
    void deleteUser(UUID userId) throws UserNotFoundException;
}
