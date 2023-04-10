package com.daruo.firstweb.controller;


import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/register")
    public String register() {
        return "register";
    }

    @GetMapping("/users/home")
    public String home(Model model) {

        List<User> userList = userService.getAllUsers();

        model.addAttribute("users", userList);

        return "home";
    }

    @GetMapping("/users/logout")
    public String logout() {
        return "redirect:/users/login";
    }

    @GetMapping("users/shop")
    public String shop() {
        return "shop";
    }

}
