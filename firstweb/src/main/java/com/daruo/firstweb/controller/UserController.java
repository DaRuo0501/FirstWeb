package com.daruo.firstweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/register")
    public String register() {
        return "register";
    }

    @GetMapping("/users/home")
    public String home() {
        return "home";
    }

}
