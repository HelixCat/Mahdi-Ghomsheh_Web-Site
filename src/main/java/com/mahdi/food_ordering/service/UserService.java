package com.mahdi.food_ordering.service;

import com.mahdi.food_ordering.model.User;
import com.mahdi.food_ordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        List<User> userList = (List<User>) repository.findAll();
        return userList;
    }
}
