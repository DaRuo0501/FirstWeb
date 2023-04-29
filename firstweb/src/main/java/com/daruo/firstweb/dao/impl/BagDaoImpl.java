package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
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
    public void createBag(Integer userId, TempBag tempBag) {

        String sql = "INSERT INTO bag (user_id, pokemon_id, pokemon_name, image_url, category, " +
                "hp, lv, exp, attack, defense, speed, price, stock, " +
                "skill_1, skill_2, skill_3, skill_4, create_date, last_modified_date) " +
                "VALUES (:userId, :pokemonId, :pokemonName, :imageUrl, :category, " +
                ":hp, :lv, :exp, :attack, :defense, :speed, :price, :stock, " +
                ":skill1, :skill2, skill3, skill4, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pokemonId", tempBag.getPokemonId());
        map.put("pokemonName", tempBag.getPokemonName());
        map.put("imageUel", tempBag.getImageUrl());
        map.put("category", tempBag.getCategory());
        map.put("hp", tempBag.getHp());
        map.put("lv", tempBag.getLv());
        map.put("exp", tempBag.getExp());
        map.put("attack", tempBag.getAttack());
        map.put("defense", tempBag.getDefense());
        map.put("speed", tempBag.getSpeed());
        map.put("price", tempBag.getPrice());
        map.put("stock", tempBag.getStock());
        map.put("skill1", tempBag.getSkill1());
        map.put("skill2", tempBag.getSkill2());
        map.put("skill3", tempBag.getSkill3());
        map.put("skill4", tempBag.getSkill4());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

    }

    // 查詢 背包
    @Override
    public List<TempBag> getBag(Integer userId) {

        String sql = "SELECT * from bag  WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        return tempBagList;
    }
}
