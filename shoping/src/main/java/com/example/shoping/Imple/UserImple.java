package com.example.shoping.Imple;

import com.example.shoping.dto.UserDto;
import com.example.shoping.entities.User;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserImple implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(String role,UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setRole(role);
        User user=this.modelMapper.map(userDto,User.class);
        User createdUser=this.userRepository.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);
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

    @Override
    public UserDto login(UserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        // Find user by email
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        System.out.println(email);
        System.out.println(password);

        if (user != null && user.getPassword().equals(password)) {

            userDto.setRole(user.getRole());
            userDto.setName(user.getName());
            userDto.setUserId(user.getUserId());
            UserDto loggedInUserDto = modelMapper.map(user, UserDto.class);

            return loggedInUserDto;
        } else {
            System.out.println("hello");
            return null;
        }
    }
}
