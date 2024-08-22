package com.example.passwordcracker.Security;

import com.example.passwordcracker.Models.User;
import com.example.passwordcracker.Repos.RoleRepo;
import com.example.passwordcracker.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.passwordcracker.Models.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDataSeeder {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    public void seed(){
        if(roleRepo.findByName("Admin") ==null){
            addRole("Admin");
        }
        if(roleRepo.findByName("Client") ==null){
            addRole("Client");
        }
        if(userRepo.findByUserName("admin@cracker.se") == null){
            addUser("admin@cracker.se","Admin");
        }
        if(userRepo.findByUserName("client@cracker.se") == null) {
            addUser("client@cracker.se","Client");
        }
    }

    private void addUser(String username, String group){
        Set<Role> roles = new HashSet<>();
        Role role = roleRepo.findByName(group);


        if(role != null){
            roles.add(role);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123");
        User user = User.builder().password(hash).userName(username).role(roles).build();
        userRepo.save(user);
    }
    private void addRole(String name){
        roleRepo.save(Role.builder().name(name).build());
    }
}
