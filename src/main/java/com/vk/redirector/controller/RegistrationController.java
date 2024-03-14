package com.vk.redirector.controller;

import com.vk.redirector.entity.User;
import com.vk.redirector.security.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public String register(
            @RequestParam @NotNull String username,
            @RequestParam @NotNull String password
    ) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        try {
            userService.saveUser(newUser);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            return String.format("redirect:/register?error=%s", e.getMessage());
        }
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
