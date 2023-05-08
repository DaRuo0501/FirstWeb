package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.*;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface UserService {

    String register(UserRegisterRequest userRegisterRequest);

    TempUser login(UserLoginRequest userLoginRequest);

    List<TempUser> getAllUsers(UserQueryParams userQueryParams);

    void deleteUserById(Integer userId);

    void updateUser(UserUpdateRequest userUpdateRequest);

    TempUser getUserById(Integer userId);

    Integer countUser(UserQueryParams userQueryParams);
}
