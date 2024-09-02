package com.example.passwordcracker.Controller;

import com.example.passwordcracker.DTO.UserDTO;
import com.example.passwordcracker.Models.Role;
import com.example.passwordcracker.Models.User;
import com.example.passwordcracker.Repos.RoleRepo;
import com.example.passwordcracker.Repos.UserRepo;
import com.example.passwordcracker.Security.UserDetailServiceIMPL;
import com.example.passwordcracker.Security.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private final UserDetailServiceIMPL userDetailService;
    private final UserService userService;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;


    public HomeController(UserDetailServiceIMPL userDetailService, UserService userService, RoleRepo roleRepo, UserRepo userRepo) {
        this.userDetailService = userDetailService;
        this.userService = userService;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    String index() {
        return "index";
    }

    @GetMapping("/cracker")
    String cracker(Model model) {
        if(! model.containsAttribute("sha")) {
            model.addAttribute("sha","");
            model.addAttribute("md5","");
            model.addAttribute("password","");
        }
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

    @GetMapping("/loginerror")
    String loginerror() {
        return "loginerror";
    }

    @GetMapping("/register")
    String register(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") UserDTO dto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error ->{
                redirectAttributes.addFlashAttribute(error.getField() + "Error" , error.getDefaultMessage());
            });
            return "redirect:/register";
        }
        Role role = roleRepo.findByName("Client");
        User user = userService.dtoToUser(dto);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        userRepo.save(user);
        return "redirect:/";
    }


}
