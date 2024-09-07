package com.fitstam.User.UserService.controller;

import com.fitstam.User.UserService.entities.User;
import com.fitstam.User.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    //create
    @PostMapping("/createUser")
    public ResponseEntity<User> createUSer(@RequestBody User user){
        User user1 = service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    //getAll
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = service.getAllUser();
        return  ResponseEntity.ok(allUser);
    }


    //getSigle
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User user = service.getUser(userId);
        return ResponseEntity.ok(user);
    }


}
