package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.BoxDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempBox;
import com.daruo.firstweb.rowmapper.TempBoxRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BoxDaoImpl implements BoxDao {

    private final static Logger log = LoggerFactory.getLogger(BoxDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 新增 商品 至 背包
    @Override
    public void createBox(Integer userId, Integer myPkId, Integer boxId) {

        try {

            String sql = "INSERT INTO box (box_id, user_id, my_pk_id, created_date, last_modified_date) " +
                    "VALUES (:boxId, :userId, :myPkId, :createdDate, :lastModifiedDate);";

            Map<String, Object> map = new HashMap<>();
            map.put("boxId", boxId + 1);
            map.put("userId", userId);
            map.put("myPkId", myPkId);

            Date now = new Date();
            map.put("createdDate", now);
            map.put("lastModifiedDate", now);

            namedParameterJdbcTemplate.update(sql, map);

        } catch (Exception e) {

            log.error(e.toString());
        }
    }

    @Override
    public Integer getLastBoxIdByUserId(Integer userId) {

        String sql = "SELECT box_id, user_id, my_pk_id, created_date, last_modified_date " +
                "FROM box WHERE user_id = :userId ORDER BY box_id DESC;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBox> tempBoxList = namedParameterJdbcTemplate.query(sql, map , new TempBoxRowMapper());

        if (tempBoxList.size() > 0) {

            return tempBoxList.get(0).getBoxId();

        } else {

            return 0;
        }
    }

    @Override
    public void createBoxByTempBag(TempBag tempBag, Integer boxId) {

        String sql = "INSERT INTO box (box_id, user_id, pokemon_id, pokemon_name, pokemon_image_url, category, " +
                "hp, lv, exp, attack, defense, speed, price, description, " +
                "created_date, last_modified_date) " +
                "VALUES (:boxId, :userId, :pokemonId, :pokemonName, :pokemonImageUrl, :category, " +
                ":hp, :lv, :exp, :attack, :defense, :speed, :price, :description, " +
                ":createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("boxId", boxId + 1);
        map.put("userId", tempBag.getUserId());
        map.put("pokemonId", tempBag.getPokemonId());
        map.put("pokemonName", tempBag.getPokemonName());
        map.put("pokemonImageUrl", tempBag.getPokemonImageUrl());
        map.put("category", tempBag.getCategory().toString());
        map.put("hp", tempBag.getHp());
        map.put("lv", tempBag.getLv());
        map.put("exp", tempBag.getExp());
        map.put("attack", tempBag.getAttack());
        map.put("defense", tempBag.getDefense());
        map.put("speed", tempBag.getSpeed());
        map.put("price", tempBag.getPrice());
        map.put("description", tempBag.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
    }
}
