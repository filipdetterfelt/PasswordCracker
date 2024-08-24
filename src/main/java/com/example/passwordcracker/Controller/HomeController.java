package com.example.passwordcracker.Controller;

import com.example.passwordcracker.Security.UserDetailServiceIMPL;
import com.example.passwordcracker.Security.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final UserDetailServiceIMPL userDetailService;
    private final UserService userService;

    public HomeController(UserDetailServiceIMPL userDetailService, UserService userService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @GetMapping("/")
    String index() {
        return "index";
    }

    @GetMapping("/cracker")
    String cracker() {
        return "cracker";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping("logincheck")
    String logincheck(Model model, @RequestParam ("username") String username, @RequestParam ("password")String password) {
        try{
            if(!userService.checkValidateUser(username, password)){
                return "redirect:/login?error";
            }
            else{
                return "redirect:/cracker";
            }
        }
        catch(Exception e){
            return "redirect:/login?userNotFound";
        }
    }
}
