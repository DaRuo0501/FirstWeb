package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.UserLoginRequest;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // 註冊
    @PostMapping("/users/register")
    public String register(@ModelAttribute("user") @Valid UserRegisterRequest userRegisterRequest) {

        if (userRegisterRequest.getPassword().equals(userRegisterRequest.getPassword1())) {

            String user = userService.register(userRegisterRequest);

            if (user == "login") {
                System.out.println("註冊成功，前往登入頁面");
                return "login";
            } else {
                return "register";
            }
        } else {
            System.out.println("確認密碼錯誤");
            return "register";
        }
    }

    // 登入
    @PostMapping("/users/login")
    public String login(@ModelAttribute @Valid UserLoginRequest userLoginRequest,
                        Model model,
                        HttpSession session,

                        // 分頁 Pagination
                        @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                        @RequestParam(defaultValue = "0") @Min(0) Integer offset

    ) {

        User user = userService.login(userLoginRequest);

        model.addAttribute("user1", user);  // 將登入的用戶傳給 home 頁面

        session.setAttribute("showUserName", user);

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        if (user == null) {
            return "redirect:login";
        } else {
            List<User> userList = userService.getAllUsers(userQueryParams);
            model.addAttribute("users", userList);

            log.info("登入帳號為:" + user.getUserName());

            return "home";
        }
    }

    // 刪除
    @GetMapping("/users/deleteUser/{userId}")
    public String delete(@PathVariable(name = "userId") Integer userId) {

        userService.deleteUserById(userId);

        return "redirect:home";
    }

    // 修改
    @PostMapping("/users/update")
    public String update(@ModelAttribute UserUpdateRequest userUpdateRequest) {

        userService.updateUser(userUpdateRequest);

        return "redirect:home";
    }

    // 前往修改頁面
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
}
