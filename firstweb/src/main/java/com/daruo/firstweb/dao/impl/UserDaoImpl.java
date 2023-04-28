package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.UserDao;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.dto.UserRegisterRequest;
import com.daruo.firstweb.dto.UserUpdateRequest;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 新增
    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO user(user_name, password, email, money, created_date, last_modified_date) " +
                "VALUES (:userName, :password, :email, :money, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userName", userRegisterRequest.getUserName());
        map.put("password", userRegisterRequest.getPassword());
        map.put("email", userRegisterRequest.getEmail());
        map.put("money", 100);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    // 使用 帳號 查詢 單一個
    @Override
    public User getUserByName(String userName) {

        String sql = "SELECT user_id, user_name, password, email, money, created_date, last_modified_date " +
                "FROM user WHERE user_name = :userName;";

        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    // 使用 信箱 查詢
    @Override
    public User getUserByEmail(String userEmail) {
        String sql = "SELECT user_id, user_name, password, email, money, created_date, last_modified_date " +
                "FROM user WHERE email = :email;";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userEmail);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    // 查詢全部
    @Override
    public List<User> getAllUsers(UserQueryParams userQueryParams) {

        String sql = "SELECT user_id, user_name, password, email, money, created_date, last_modified_date " +
                "FROM user WHERE 1 = 1";

        Map<String, Object> map = new HashMap<>();

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", userQueryParams.getLimit());
        map.put("offset", userQueryParams.getOffset());

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
    }

    // 使用 ID 刪除
    @Override
    public void deleteUserById(Integer userId) {

        String sql = "DELETE FROM user WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 更新
    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {

        String sql = "UPDATE user SET user_id = :userId, user_name = :userName, password = :password, email = :email, money = :money " +
                "WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userUpdateRequest.getUserId());
        map.put("userName", userUpdateRequest.getUserName());
        map.put("password", userUpdateRequest.getPassword());
        map.put("email", userUpdateRequest.getEmail());
        map.put("money", userUpdateRequest.getMoney());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 使用 ID 查詢
    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, user_name, password, email, money, created_date, last_modified_date " +
                "FROM user WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    // 使用 帳號 查詢 多個
    @Override
    public List<User> getUsersByName(String userName) {

        String sql = "SELECT user_id, user_name, password, email, money, created_date, last_modified_date " +
                "FROM user WHERE user_name like :userName";

        Map<String, Object> map = new HashMap<>();
        map.put("userName", "%" + userName + "%");

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
    }

    @Override
    public void updateUserMoney(int userId, int newMoney) {

        String sql = "UPDATE user SET money = :money WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("money", newMoney);

        namedParameterJdbcTemplate.update(sql, map);

    }
}
