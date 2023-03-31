package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.UserLoginRequest;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface UserService {

    String register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

    List<User> getAllUsers();

    void deleteUserById(Integer userId);

    void updateUser(UserUpdateRequest userUpdateRequest);

    User getUserById(Integer userId);



}
