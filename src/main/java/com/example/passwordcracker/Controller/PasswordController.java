package com.example.passwordcracker.Controller;

import com.example.passwordcracker.Security.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordController {

     private final FileConfig fileConfig;
     String savedPassword = "";

     @Autowired
     public PasswordController(FileConfig fileConfig) {
         this.fileConfig = fileConfig;
     }


    @PostMapping("/submit-password")
    public String savePassword(@RequestParam String password, RedirectAttributes rda) {
        savedPassword = password;
        String md5 = fileConfig.MD5(password);
        String sha = fileConfig.sha256(password);
        rda.addFlashAttribute("md5", md5);
        rda.addFlashAttribute("sha", sha);
        rda.addFlashAttribute("password", password);

        fileConfig.fileWriting(password);
        System.out.println(savedPassword);
        return "redirect:/cracker";
    }


    @GetMapping("/success")
    public String success(Model model) {
        if(!model.containsAttribute("password")) {
            model.addAttribute("password", "");
        }
        return "success";
    }

    @PostMapping("/passwordis")
    public String searchPassword(@RequestParam String searchHash,
                                 @RequestParam String hashType,
                                 RedirectAttributes rda) {

        String password = "";
        if("md5".equals(hashType)){
            password = fileConfig.deHashMD5(searchHash);
        }
        else if("sha256".equals(hashType)){
            password = fileConfig.deHashSHA256(searchHash);
        }

        rda.addFlashAttribute("password", password);
        return "redirect:/success";
    }

}


