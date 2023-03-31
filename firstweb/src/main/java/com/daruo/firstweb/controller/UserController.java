package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.UserLoginRequest;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public String register(HttpServletRequest request, @ModelAttribute @Valid UserRegisterRequest userRegisterRequest) {
        HttpSession session = request.getSession();

        if (userRegisterRequest.getPassword().equals(userRegisterRequest.getPassword1())) {

            String user = userService.register(userRegisterRequest);

            if (user == "login") {
                return "login";
            } else {
                System.out.println("帳號已被使用");
                return "register";
            }
        } else {
            System.out.println("確認密碼錯誤");
            return "register";
        }
    }

    @PostMapping("/users/login")
    public String login(HttpServletRequest request,@ModelAttribute @Valid UserLoginRequest userLoginRequest,
                        Model model) {
//        HttpSession session = request.getSession();
        User user = userService.login(userLoginRequest);

        if (user == null) {
            return "login";
        } else {
            List<User> userList = userService.getAllUsers();
            model.addAttribute("user1", user);
            model.addAttribute("users", userList); // 將登入的用戶傳給 home 頁面
//            session.setAttribute("showUserName",user.getUserName());
            return "redirect:/users/home";
        }
    }

    @GetMapping("/users/deleteUser/{userId}")
    public String delete(@PathVariable(name = "userId") Integer userId) {

        userService.deleteUserById(userId);

        return "redirect:/users/home";
    }

    @PostMapping("/users/update")
    public String update(@ModelAttribute UserUpdateRequest userUpdateRequest) {

        userService.updateUser(userUpdateRequest);

        return "redirect:/users/home";
    }

    @GetMapping("/users/goToUpdatePage/{userId}")
    public String goToUpdateUserPage(@PathVariable(name = "userId") Integer userId,
                                     Model model) {

        User user = userService.getUserById(userId);

        model.addAttribute("updateUser", user);

        return "userUpdate";
    }

}
