package com.namrata.projectbrain.controller;

import java.util.Collection;


import com.namrata.projectbrain.form.UserForm;
import com.namrata.projectbrain.model.User;
import com.namrata.projectbrain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/user/register")
    public User save(@RequestBody UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUsername(userForm.getUsername());
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());

        return userRepository.save(user);
    }
}