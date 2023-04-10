package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserByName(String userName);

    User getUserByEmail(String userEmail);

    List<User> getAllUsers();

    void deleteUserById(Integer userId);

    void updateUser(UserUpdateRequest userUpdateRequest);

    User getUserById(Integer userId);

//    User getUserByVO(UserVO tempVO);

}
