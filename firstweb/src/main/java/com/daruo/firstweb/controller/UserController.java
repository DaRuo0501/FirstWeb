package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.*;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
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

        TempUser tempUser = userService.login(userLoginRequest);

        // 將登入的用戶存在 session 再傳給 home 頁面
        session.setAttribute("showUserName", tempUser);

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        if (tempUser == null) {
            return "redirect:login";
        } else {

            List<TempUser> tempUserList = userService.getAllUsers(userQueryParams);

            model.addAttribute("users", tempUserList);

            log.info("登入帳號為:" + tempUser.getUserName());

            return "redirect:/users/home";
        }
    }

    // 使用 頁數 查詢
    @GetMapping("/users/page")
    public String next(Model model,

                       // 分頁 Pagination
                       @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                       @RequestParam(defaultValue = "0") @Min(0) Integer offset

    ) {

        offset = offset * 5 - 5;

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        List<TempUser> tempUserList = userService.getAllUsers(userQueryParams);

        model.addAttribute("users", tempUserList);

        return "userList";
    }

    // 修改
    @PostMapping("/users/update")
    public String update(@ModelAttribute UserUpdateRequest userUpdateRequest) {

        userService.updateUser(userUpdateRequest);

        return "redirect:/users/userList";
    }

    // 刪除
    @GetMapping("/users/deleteUser/{userId}")
    public String delete(@PathVariable(name = "userId") Integer userId) {

        userService.deleteUserById(userId);

        return "redirect:/users/userList";
    }

}
