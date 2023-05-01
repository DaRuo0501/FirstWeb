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
    public void createBag(Integer userId, TempPokemon tempPokemon) {

        String sql = "INSERT INTO bag (user_id, pokemon_id, pokemon_name, pokemon_image_url, category, " +
                "hp, lv, exp, attack, defense, speed, price, description, " +
                "skill_1, skill_2, skill_3, skill_4, created_date, last_modified_date) " +
                "VALUES (:userId, :pokemonId, :pokemonName, :pokemonImageUrl, :category, " +
                ":hp, :lv, :exp, :attack, :defense, :speed, :price, :description, " +
                ":skill1, :skill2, :skill3, :skill4, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pokemonId", tempPokemon.getPokemonId());
        map.put("pokemonName", tempPokemon.getPokemonName());
        map.put("pokemonImageUrl", tempPokemon.getPokemonImageUrl());
        map.put("category", tempPokemon.getCategory().toString());
        map.put("hp", tempPokemon.getHp());
        map.put("lv", tempPokemon.getLv());
        map.put("exp", tempPokemon.getExp());
        map.put("attack", tempPokemon.getAttack());
        map.put("defense", tempPokemon.getDefense());
        map.put("speed", tempPokemon.getSpeed());
        map.put("price", tempPokemon.getPrice());
        map.put("description", tempPokemon.getDescription());
        map.put("skill1", tempPokemon.getSkill1());
        map.put("skill2", tempPokemon.getSkill2());
        map.put("skill3", tempPokemon.getSkill3());
        map.put("skill4", tempPokemon.getSkill4());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

    }

    // 查詢 背包
    @Override
    public List<TempBag> getBag(Integer userId) {

        String sql = "SELECT user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "category, hp, lv, exp, attack, defense, speed, price, description, " +
                "skill_1, skill_2, skill_3, skill_4, " +
                "created_date, last_modified_date " +
                "from bag  WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        return tempBagList;
    }
}
