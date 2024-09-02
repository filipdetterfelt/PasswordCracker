package com.example.passwordcracker.Security;

import com.example.passwordcracker.DTO.UserDTO;
import com.example.passwordcracker.Models.User;
import com.example.passwordcracker.Repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    UserRepo userRepo;

    public User dtoToUser(UserDTO dto){
        return User.builder()
                .id(dto.getId())
                .userName(dto.getUserName())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .phone(dto.getPhone())
                .password(hashPassword(dto.getPassword()))
                .build();
    }

    public UserDTO userToDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

    public String hashPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public Boolean checkValidateUser(String user, String password){
        User user1 = userRepo.findByUserName(user);
        if(user1 == null) throw new UsernameNotFoundException("User not found");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, user1.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);

        if(user == null) throw new UsernameNotFoundException("User not found");
        return new ConcreteUserDetails(user);
    }
}
