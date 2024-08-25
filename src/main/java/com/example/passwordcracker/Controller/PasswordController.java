package com.example.passwordcracker.Controller;

import com.example.passwordcracker.Security.FileConfig;
import com.example.passwordcracker.Security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {

     FileConfig fileConfig;

     @Autowired
     public PasswordController(FileConfig fileConfig) {
         this.fileConfig = fileConfig;
     }

    @PostMapping("/submit-password")
    public String submitPassword(String password , Model model) {
         model.addAttribute("password", password);
        fileConfig.fileWritingAndReading(password);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
