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

    @PostMapping("/submit-password")
    public String submitPassword(@RequestParam String password, Model model) {
        fileConfig.fileWritingAndReading(password);
        model.addAttribute("password", password);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
