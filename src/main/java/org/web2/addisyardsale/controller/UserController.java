package org.web2.addisyardsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web2.addisyardsale.dto.LoginRequest;
import org.web2.addisyardsale.dto.SignupRequest;
import org.web2.addisyardsale.model.User;
import org.web2.addisyardsale.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return userService.authenticate(loginRequest);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest signupRequest) {
        return userService.register(signupRequest);
    }



}

