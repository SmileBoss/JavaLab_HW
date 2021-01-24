package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UsersService usersService;

    @GetMapping
    public String getUsers(@RequestParam(value = "userId") String userIdAsString, HttpServletResponse resp, Model model){
        Long userId = Long.parseLong(userIdAsString);
        Optional<User> userOptional = usersService.getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "usersPage";
        } else {
            resp.setStatus(404);
            return "errorPage";
        }
    }

    @PostMapping
    public String postUsers(@RequestParam(value = "action", required = false) String action,
                            @RequestParam(value = "userId", required = false) String userId) {
        if (action != null && action.equals("delete")) {
            usersService.deleteUserById(Long.parseLong(userId));
        }
        return "redirect:/profile";
    }

}
