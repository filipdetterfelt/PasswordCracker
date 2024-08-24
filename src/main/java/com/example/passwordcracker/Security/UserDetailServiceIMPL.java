package com.example.passwordcracker.Security;

import com.example.passwordcracker.Models.User;
import com.example.passwordcracker.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceIMPL implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new ConcreteUserDetails(user);
    }
}
