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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    // 登入頁面
    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    // 註冊頁面
    @GetMapping("/users/register")
    public String register() {
        return "register";
    }

    // 首頁
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

    // 修改使用者頁面
    @GetMapping("/users/goToUpdatePage/{userId}")
    public String goToUpdateUserPage(@PathVariable(name = "userId") Integer userId,
                                     Model model) {

        User user = userService.getUserById(userId);

        model.addAttribute("updateUser", user);

        if (user == null) {
            throw new RuntimeException();
        } else {

            return "userUpdate";
        }
    }

    // 商城頁面
    @GetMapping("users/shop")
    public String shop() {
        return "shop";
    }

    // 登出(返回登入頁面)
    @GetMapping("/users/logout")
    public String logout() {
        return "redirect:/users/login";
    }

}
