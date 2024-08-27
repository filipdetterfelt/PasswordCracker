package com.example.passwordcracker;

import com.example.passwordcracker.Security.FileConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


public class HashPasswordApp implements CommandLineRunner {
    private  final FileConfig fc;
    private ConfigurableApplicationContext context;

    public HashPasswordApp() {
        this.fc = new FileConfig();
    }
    @Override
    public void run(String... args) throws Exception {
        fc.crackPassword();
    }
}
