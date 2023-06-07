package com.example.shoping.services;

import com.example.shoping.entities.User;
import com.example.shoping.utils.UserBody;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(String role, User userDto);
    User getUserByEmail(String email);
    User login(UserBody userBody);

    //Future works
    User updateUserById(User userDto,String userId);
    User getUserById(String id);


}
