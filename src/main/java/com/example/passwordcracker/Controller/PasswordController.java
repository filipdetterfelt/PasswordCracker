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
     String savedPassword = "";

     @Autowired
     public PasswordController(FileConfig fileConfig) {
         this.fileConfig = fileConfig;
     }

   /* @PostMapping("/submit-password")
    public String submitPassword(String password , Model model) {
         model.addAttribute("password", password);
        fileConfig.fileWriting(password);
        return "redirect:/success";
    }*/

    @PostMapping("/submit-password")
    public String savePassword(@RequestParam String password, Model model) {
        savedPassword = password;
        String md5 = fileConfig.MD5(password);
        String sha = fileConfig.sha256(password);
        model.addAttribute("savedPassword", savedPassword);
        model.addAttribute("md5" , md5);
        model.addAttribute("sha", sha);
        fileConfig.fileWriting(password);
        System.out.println(savedPassword);
        return "success";
    }

    @GetMapping("/success")
    public String success(Model model) {
         model.addAttribute("savedPassword", savedPassword);
        return "success";
    }
}
