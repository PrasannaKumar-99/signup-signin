package com.example.online.store.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.online.store.entity.User;
import com.example.online.store.service.UserService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        
        userService.registerUser(user);
        model.addAttribute("message", "Registration Successful!");
        return "signin";
    }
    
    @GetMapping("/signin")
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }
    
    @PostMapping("/signin")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<User> user = userService.authenticate(email, password);
        if (user.isPresent()) {
            model.addAttribute("username", user.get().getUsername());
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "signin";
        }
    }
}
