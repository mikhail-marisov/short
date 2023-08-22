package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;

import javax.management.InstanceNotFoundException;

public interface UserService {

    User create(User user);
    void delete(Long id);
    User findByName(String name) throws InstanceNotFoundException;

}
