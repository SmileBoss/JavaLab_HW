package ru.itis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.models.User;
import ru.itis.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/addUser")
    public String createUserPage(){
        return "createUser";
    }

    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute("user") User user){
        userService.addNewUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String updateUserPage(@PathVariable("id") Integer id,  Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/update")
    public String updateUser( @ModelAttribute User user){
        userService.updateUserById(user);
        return "redirect:/users";
    }
}
