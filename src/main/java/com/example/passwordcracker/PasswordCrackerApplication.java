package com.example.passwordcracker;

import com.example.passwordcracker.Security.FileConfig;
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

    @Autowired
    FileConfig fileConfig;

    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            SpringApplication.run(PasswordCrackerApplication.class, args);
        }
        else if(args[0].equals("HashPasswordApp") ) {
            new HashPasswordApp().run();
        }
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            userDataSeeder.seed();
            //fileConfig.crackPassword();
        };
    }





   /* @Bean
    CommandLineRunner hashPassword() {
        return args -> {
         security.fileWritingAndReading();
        };
    }*/

}
