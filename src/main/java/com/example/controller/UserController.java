package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RestController
@RequestMapping("/short/user/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/get")
    public User getUserByName(@RequestBody String name) throws InstanceNotFoundException {
        return userService.findByName(name);
    }
}
