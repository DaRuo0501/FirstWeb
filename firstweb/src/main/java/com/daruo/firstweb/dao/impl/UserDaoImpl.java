package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.UserDao;
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

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO user(user_name, password, email, created_date, last_modified_date) " +
                "VALUES (:userName, :password, :email, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userName", userRegisterRequest.getUserName());
        map.put("password", userRegisterRequest.getPassword());
        map.put("email", userRegisterRequest.getEmail());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserByName(String userName) {

        String sql = "SELECT user_id, user_name, password, email, created_date, last_modified_date " +
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

    @Override
    public User getUserByEmail(String userEmail) {
        String sql = "SELECT user_id, user_name, password, email, created_date, last_modified_date " +
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

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT user_id, user_name, password, email, created_date, last_modified_date " +
                "FROM user;";

        Map<String, Object> map = new HashMap<>();

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
    }

    @Override
    public void deleteUserById(Integer userId) {

        String sql = "DELETE FROM user WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {

        String sql = "UPDATE user SET user_id = :userId, user_name = :userName, " +
                "password = :password, email = :email " +
                "WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userUpdateRequest.getUserId());
        map.put("userName", userUpdateRequest.getUserName());
        map.put("password", userUpdateRequest.getPassword());
        map.put("email", userUpdateRequest.getEmail());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, user_name, password, email, created_date, last_modified_date " +
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
}
