package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.*;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface UserService {

    String register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

    List<User> getAllUsers(UserQueryParams userQueryParams);

    void deleteUserById(Integer userId);

    void updateUser(UserUpdateRequest userUpdateRequest);

    User getUserById(Integer userId);
}
