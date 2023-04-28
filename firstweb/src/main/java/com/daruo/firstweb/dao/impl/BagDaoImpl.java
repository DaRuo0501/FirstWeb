package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.rowmapper.TempBagRowMapper;
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
public class BagDaoImpl implements BagDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 新增 商品 至 背包
    @Override
    public void createBag(Integer userId, Integer pokemonId, String pokemonName) {

        String sql = "INSERT INTO bag (user_id, pokemon_id, create_date, last_modified_date) " +
                "VALUES (:userId, :pokemonId, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pokemonId", pokemonId);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

    }

    // 查詢 背包
    @Override
    public List<TempBag> getBag(Integer userId) {

        String sql = "SELECT * from bag b, pokemon p WHERE b.pokemon_id = p.pokemon_id AND user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        return tempBagList;
    }
}
