package com.fitstam.User.UserService.services;

import com.fitstam.User.UserService.entities.User;

import java.util.List;

public interface UserService {
    //save user
    User saveUser(User user);
    //get All user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);


}
