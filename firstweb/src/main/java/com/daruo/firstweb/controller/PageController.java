package com.daruo.firstweb.controller;


import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model,

                       // 分頁 Pagination
                       @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                       @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        List<User> userList = userService.getAllUsers(userQueryParams);

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
