package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.OrderDao;
import com.daruo.firstweb.dto.TempOrder;
import com.daruo.firstweb.model.Order;
import com.daruo.firstweb.rowmapper.TempOrderRowMapper;
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
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createOrderById(Order order) {

        String sql = "INSERT INTO `order`(user_id, total_amount, created_date, last_modified_date) " +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", order.getUserId());
        map.put("totalAmount", order.getTotalAmount());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

    }

    @Override
    public List<TempOrder> getOrderById(Integer userId) {

        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date " +
                "FROM `order` WHERE user_id = :userId ORDER BY order_id DESC;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempOrder> tempOrderList = namedParameterJdbcTemplate.query(sql, map, new TempOrderRowMapper());

        return tempOrderList;
    }
}
