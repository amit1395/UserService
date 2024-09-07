package com.fitstam.User.UserService.repo;

import com.fitstam.User.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
