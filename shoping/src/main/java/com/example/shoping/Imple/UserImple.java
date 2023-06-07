package com.example.shoping.Imple;


import com.example.shoping.entities.User;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.UserService;
import com.example.shoping.utils.UserBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public User getUserByEmail(String email) {
        try{
            User user=this.userRepository.findByEmail(email);
            if(user!=null){
                return  user;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public User login(UserBody userBody) {
        String email = userBody.getEmail();
        String password = userBody.getPassword();
        BCryptPasswordEncoder b=new BCryptPasswordEncoder();

        User user = userRepository.findByEmail(email);
        String encodedPassword=user.getPassword();
        boolean passwordsMatch = b.matches(password, encodedPassword);
        if (user != null && passwordsMatch) {
            return user;
        } else {
            return null;
        }
    }


    //Future works

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


}
