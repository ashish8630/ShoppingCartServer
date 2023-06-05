package com.example.shoping.controller;


import com.example.shoping.entities.User;
import com.example.shoping.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/{role}")
    public ResponseEntity<User> createUser(@PathVariable String role, @RequestBody User userDto){
        User userDto1=this.userService.createUser(role,userDto);
        return ResponseEntity.ok(userDto1);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getAllUser(@PathVariable String userId){
        User userDto1=this.userService.getUserById(userId);
        return ResponseEntity.ok(userDto1);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User userDto,@PathVariable String userId){
        User userDto1=this.userService.updateUserById(userDto,userId);
        return ResponseEntity.ok(userDto1);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userDto) {
        User userDto1 = this.userService.login(userDto);
        return ResponseEntity.ok().body(userDto1);
    }
    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        User user=this.userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
