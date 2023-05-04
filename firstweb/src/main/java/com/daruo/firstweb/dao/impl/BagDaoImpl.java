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
    public void createBag(Integer userId, TempPokemon tempPokemon, Integer bagId) {

        String sql = "INSERT INTO bag (bag_id, user_id, pokemon_id, pokemon_name, pokemon_image_url, category, " +
                "hp, lv, exp, attack, defense, speed, price, description, " +
                "created_date, last_modified_date) " +
                "VALUES (:bagId, :userId, :pokemonId, :pokemonName, :pokemonImageUrl, :category, " +
                ":hp, :lv, :exp, :attack, :defense, :speed, :price, :description, " +
                ":createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("bagId", bagId + 1);
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

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

    }

    // 查詢 背包
    @Override
    public List<TempBag> getBag(Integer userId) {

        String sql = "SELECT bag_id, user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "category, hp, lv, exp, attack, defense, speed, price, description, " +
                "created_date, last_modified_date " +
                "FROM bag  WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        return tempBagList;
    }

    // 取得 背包內的 排在最後的 商品
    @Override
    public Integer getLastBagIdByUserId(Integer userId) {

        String sql = "SELECT bag_id, user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "category, hp, lv, exp, attack, defense, speed, price, description, " +
                "created_date, last_modified_date " +
                "FROM bag WHERE user_id = :userId ORDER BY bag_id DESC;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        if (tempBagList.size() > 0) {

            return tempBagList.get(0).getBagId();

        } else {

            return 0;
        }
    }

    // 刪除 指定的 背包 ID 的 商品
    @Override
    public void deleteBagId(Integer userId, Integer bagId) {

        String sql = "DELETE FROM bag WHERE user_id = :userId AND bag_id = :bagId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("bagId", bagId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 取得 排在 指定的 背包 ID 之後的 所有商品
    @Override
    public List<TempBag> getBags(Integer userId, Integer bagId) {

        String sql = "SELECT bag_id, user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "category, hp, lv, exp, attack, defense, speed, price, description, " +
                "created_date, last_modified_date " +
                "FROM bag WHERE user_id = :userId LIMIT 6 OFFSET :OFFSET";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("OFFSET", bagId - 1);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        return tempBagList;
    }

    // 更新 向前填補被刪除的商品 所空缺的背包 ID
    @Override
    public void updateBagId(Integer userId, Integer bagId, Integer newBagId) {

        String sql = "UPDATE bag SET bag_id = :newBagId WHERE user_id = :userId AND bag_id = :bagId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("bagId", bagId);
        map.put("newBagId", newBagId - 1);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public TempBag getBagById(Integer userId, Integer bagId) {

        String sql = "SELECT bag_id, user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "category, hp, lv, exp, attack, defense, speed, price, description, " +
                "created_date, last_modified_date " +
                "FROM bag WHERE user_id = :userId AND bag_id = :bagId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("bagId", bagId);

        List<TempBag> tempBagList = namedParameterJdbcTemplate.query(sql, map , new TempBagRowMapper());

        if (tempBagList.size() > 0) {

            return tempBagList.get(0);

        }

        return null;
    }
}
