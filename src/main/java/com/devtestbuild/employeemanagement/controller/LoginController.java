package com.devtestbuild.employeemanagement.controller;

import com.devtestbuild.employeemanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        boolean authenticated =
                userService.authenticateUser(username, password);

        if (authenticated) {
            return "redirect:/dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid username or password"
        );

        return "login";
    }
}