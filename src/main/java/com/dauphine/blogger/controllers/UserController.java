package com.dauphine.blogger.controllers;

import com.dauphine.blogger.controllers.requestbody.UserDto;
import com.dauphine.blogger.models.User;
import com.dauphine.blogger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        try {
            User user = userService.registerUser(userDto.username(), userDto.password());
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the user.");
        }
    }

}
