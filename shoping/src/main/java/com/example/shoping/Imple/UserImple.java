package com.example.shoping.Imple;

import com.example.shoping.dto.UserDto;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserImple implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(String role, UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setRole(role);
        BCryptPasswordEncoder b=new BCryptPasswordEncoder();
        String password=b.encode(userDto.getPassword());
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(password);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDto.class);
    }

    @Override
    public UserDto updateUserById(UserDto userDto, String userId) {
        User user=this.userRepository.findById(userId).orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        User updateUser=this.userRepository.save(user);
        return this.modelMapper.map(updateUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(String id) {
        User user=this.userRepository.findById(id).orElseThrow();
        return this.modelMapper.map(user,UserDto.class);

    }

    public UserDto login(UserDto userDto) {
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
            UserDto loggedInUserDto = modelMapper.map(user, UserDto.class);

            return loggedInUserDto;
        } else {
            return null;
        }
    }
}
