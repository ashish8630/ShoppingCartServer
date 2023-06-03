package com.example.shoping.controller;

import com.example.shoping.dto.UserDto;
import com.example.shoping.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/{role}")
    public ResponseEntity<UserDto> createUser(@PathVariable String role, @RequestBody UserDto userDto){
        UserDto userDto1=this.userService.createUser(role,userDto);
        return ResponseEntity.ok(userDto1);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getAllUser(@PathVariable String userId){
        UserDto userDto1=this.userService.getUserById(userId);
        return ResponseEntity.ok(userDto1);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable String userId){
        UserDto userDto1=this.userService.updateUserById(userDto,userId);
        return ResponseEntity.ok(userDto1);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        UserDto userDto1 = this.userService.login(userDto);
        return ResponseEntity.ok().body(userDto1);
    }


}
