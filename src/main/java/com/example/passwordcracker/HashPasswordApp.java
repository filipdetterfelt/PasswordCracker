package com.example.passwordcracker;

import com.example.passwordcracker.Services.FileConfigService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;


public class HashPasswordApp implements CommandLineRunner {
    private  final FileConfigService fc;
    private ConfigurableApplicationContext context;

    public HashPasswordApp() {
        this.fc = new FileConfigService();
    }
    @Override
    public void run(String... args) throws Exception {
        fc.crackPassword();
    }
}
