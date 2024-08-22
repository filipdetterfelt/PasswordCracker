package com.example.passwordcracker.Repos;

import com.example.passwordcracker.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
