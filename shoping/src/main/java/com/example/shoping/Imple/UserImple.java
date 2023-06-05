package com.example.shoping.Imple;


import com.example.shoping.entities.User;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserImple implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(String role, User userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setRole(role);
        BCryptPasswordEncoder b=new BCryptPasswordEncoder();
        String password=b.encode(userDto.getPassword());

        userDto.setPassword(password);
        User createdUser = userRepository.save(userDto);
        return createdUser;
    }

    @Override
    public User updateUserById(User userDto, String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        User updateUser=this.userRepository.save(user);
        return updateUser;
    }

    @Override
    public User getUserById(String id) {
        User user=this.userRepository.findById(id).orElseThrow();
        return user;

    }

    public User login(User userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        BCryptPasswordEncoder b=new BCryptPasswordEncoder();


        // Find user by email
        User user = userRepository.findByEmail(email);
        String encodedPassword=user.getPassword();
        boolean passwordsMatch = b.matches(password, encodedPassword);
        if (user != null && passwordsMatch) {
            userDto.setRole(user.getRole());
            userDto.setName(user.getName());
            userDto.setUserId(user.getUserId());


            return userDto;
        } else {
            return null;
        }
    }
}
