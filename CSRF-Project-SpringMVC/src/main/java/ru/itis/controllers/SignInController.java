package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.interceptors.ResponseUtil;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    UsersService usersService;

    @GetMapping
    public String getSignIn(){
        return "signIn";
    }

    @PostMapping
    public String postSignIn(@RequestParam(value = "email") String email,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "redirect", required = false) String redirect,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (usersService.authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);

            if (redirect == null) {
                return "redirect:/profile";
            } else {
                return "redirect:" + redirect;
            }
        } else {
            ResponseUtil.sendForbidden(request, response);
        }

        return "redirect:/profile";
    }
}
