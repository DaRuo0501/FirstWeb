package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    TempUser getUserByName(String userName);

    TempUser getUserByEmail(String userEmail);

    List<TempUser> getAllUsers(UserQueryParams userQueryParams);

    void deleteUserById(Integer userId);

    void updateUser(UserUpdateRequest userUpdateRequest);

    TempUser getUserById(Integer userId);

    List<User> getUsersByName(String userName);

    void updateUserMoney(Integer userId, Integer updateMoney);

    TempUser getTempUserById(Integer userId);

    Integer countUser(UserQueryParams userQueryParams);
}
