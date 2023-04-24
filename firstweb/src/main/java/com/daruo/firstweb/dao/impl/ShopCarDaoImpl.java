package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.rowmapper.ShopCarListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShopCarDaoImpl implements ShopCarDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TempPokemon> getShopCarList(User user) {

        String sql = "SELECT sc.user_id, sc.buy_cnt, " +
                "poke.pokemon_id, poke.pokemon_name, poke.image_url, " +
                "poke.price, poke.stock " +
                "FROM pokemon poke, shopping_car sc " +
                "WHERE poke.pokemon_id = sc.pokemon_id " +
                "AND user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());

        List<TempPokemon> shopCarList = namedParameterJdbcTemplate.query(sql, map, new ShopCarListRowMapper());

        return shopCarList;
    }
}
