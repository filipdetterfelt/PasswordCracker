package com.example.passwordcracker.Repos;

import com.example.passwordcracker.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String email);
}
