package com.example.shoping.services;

import com.example.shoping.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(String role, User userDto);
    User updateUserById(User userDto,String userId);
    User getUserById(String id);

    User login(User userDto);

}
