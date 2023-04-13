package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.UserDao;
import com.daruo.firstweb.dto.UserLoginRequest;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    // 註冊
    public String register(UserRegisterRequest userRegisterRequest) {

        // 分別使用帳號與信箱取得資料庫內的使用者
        User userName = userDao.getUserByName(userRegisterRequest.getUserName());
        User userEmail = userDao.getUserByEmail(userRegisterRequest.getEmail());

        // 檢查註冊的 userName
        if (userName != null) {

            log.warn("該 userName: {} 已被註冊", userRegisterRequest.getUserName());
            return "register";

        // 檢查註冊的 email
        } else if (userEmail != null){

            log.warn("該 email: {} 已被註冊", userRegisterRequest.getEmail());
            return "register";
        } else {

            // 生成密碼的雜湊值
            String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
            userRegisterRequest.setPassword(hashedPassword);

            // 創建帳號
            userDao.createUser(userRegisterRequest);
            return "login";
        }
    }

    // 登入
    @Override
    public User login(UserLoginRequest userLoginRequest) {

        // 使用 userName 來取得使用者
        User user1 = userDao.getUserByName(userLoginRequest.getUserName());

        // 檢查 user 是否已註冊
        if (user1 == null) {
            return null;
        }

        // 使用 MD5 生成密碼得雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        // 比較 password
        if (user1.getPassword().equals(hashedPassword)) {
            return user1;
        } else {
            System.out.println("登入密碼錯誤");
            return null;
        }
    }

    // 查詢所有使用者
    @Override
    public List<User> getAllUsers(UserQueryParams userQueryParams) {
        return userDao.getAllUsers(userQueryParams);
    }

    // 依照 ID 刪除使用者
    @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    }

    // 更新使用者
    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {

        // 使用 ID 從資料庫 取出同一筆 使用者資料
        User user = getUserById(userUpdateRequest.getUserId());

        // 比較前端與資料庫，同一筆資料的密碼是否相同
        if (userUpdateRequest.getPassword().equals(user.getPassword())) {

            // 如果密碼相同，代表前端此次未修改密碼
            // 不需產生新的雜湊值，直接更新資料
            userDao.updateUser(userUpdateRequest);
        } else {

            // 生成密碼的雜湊值
            String hashedPassword = DigestUtils.md5DigestAsHex(userUpdateRequest.getPassword().getBytes());
            userUpdateRequest.setPassword(hashedPassword);

            // 更新資料
            userDao.updateUser(userUpdateRequest);
        }
    }

    // 使用 ID 查詢使用者
    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getUsersByName(String userName) {
        return userDao.getUsersByName(userName);
    }
}
