package com.example.passwordcracker;

import com.example.passwordcracker.Security.SecurityConfig;
import com.example.passwordcracker.Security.UserDataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PasswordCrackerApplication {

    @Autowired
    UserDataSeeder userDataSeeder;

    @Autowired
    SecurityConfig security;

    public static void main(String[] args) {
        SpringApplication.run(PasswordCrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            userDataSeeder.seed();
        };
    }





   /* @Bean
    CommandLineRunner hashPassword() {
        return args -> {
         security.fileWritingAndReading();
        };
    }*/

}
