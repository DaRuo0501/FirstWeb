package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.UserDao;
import com.daruo.firstweb.dto.UserLoginRequest;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public String register(UserRegisterRequest userRegisterRequest) {

        User user = userDao.getUserByName(userRegisterRequest.getUserName());

        if (user != null) {
            return "register";
        } else {
            userDao.createUser(userRegisterRequest);
            return "login";
        }
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user1 = userDao.getUserByName(userLoginRequest.getUserName());

        if (user1 == null) {
            return null;
        }

        if (user1.getPassword().equals(userLoginRequest.getPassword())) {
            return user1;
        } else {
            System.out.println("登入密碼錯誤");
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {
        userDao.updateUser(userUpdateRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
