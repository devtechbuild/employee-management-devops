package com.devtestbuild.employeemanagement.controller;

import com.devtestbuild.employeemanagement.entity.User;
import com.devtestbuild.employeemanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") User user,
            Model model) {

        try {
            userService.registerUser(user);
            return "redirect:/login";

        } catch (IllegalArgumentException e) {

            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}