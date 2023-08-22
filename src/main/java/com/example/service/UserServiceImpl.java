package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {

        User createdUser = new User(user.getName(), user.getPassword(), user.getRole());

        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String name) throws InstanceNotFoundException {
        return userRepository.findByName(name).orElseThrow(() -> new InstanceNotFoundException("Пользователь с name " + name + "не существет"));
    }
}
