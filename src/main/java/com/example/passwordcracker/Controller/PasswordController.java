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

   /* @PostMapping("/submit-password")
    public String submitPassword(String password , Model model) {
         model.addAttribute("password", password);
        fileConfig.fileWriting(password);
        return "redirect:/success";
    }*/

    @PostMapping("/submit-password")
    public String savePassword(@RequestParam String password, RedirectAttributes rda, Model model) {
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
         model.addAttribute("savedPassword", savedPassword);
        return "success";
    }

    @PostMapping("/passwordis")
    public String searchPassword(@RequestParam String searchHash,
                                 @RequestParam String hashType,
                                 Model model) {

        String searchPassword = "";
        if("md5".equals(hashType)){
            searchPassword = fileConfig.deHashMD5(searchHash);
        }
        else if("sha256".equals(hashType)){
            searchPassword = fileConfig.deHashSHA256(searchHash);
        }

        model.addAttribute("deHashedPassword", searchPassword);
        return "passwordis";
    }
}


