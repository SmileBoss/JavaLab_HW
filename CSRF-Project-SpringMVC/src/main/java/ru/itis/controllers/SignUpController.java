package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.User;
import ru.itis.services.UsersService;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    UsersService usersService;

    @GetMapping
    public String getSignUp() {
        return "signUp";
    }

    @PostMapping
    public void postSignUp(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password) {

        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        usersService.signUp(user);
    }
}
