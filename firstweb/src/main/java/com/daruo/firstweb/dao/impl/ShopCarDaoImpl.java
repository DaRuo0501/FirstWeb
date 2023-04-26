package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.rowmapper.ShopCarListRowMapper;
import com.daruo.firstweb.rowmapper.ShopCarRowMapper;
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

        String sql = "SELECT sc.user_id, sc.buy_cnt, sc.amount, " +
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

    @Override
    public ShopCar getBuyCnt(ShopCar shopCar) {

        String sql = "SELECT user_id, seq_no, pokemon_id, order_id, buy_cnt, amount FROM shopping_car WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());

        List<ShopCar> shopCarList = namedParameterJdbcTemplate.query(sql, map, new ShopCarRowMapper());

        if (shopCarList.size() > 0) {

            return shopCarList.get(0);
        }

        return null;
    }

    @Override
    public void addCount(ShopCar shopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt + 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void reduceCount(ShopCar shopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt - 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void deletePokemonById(ShopCar shopCar) {

        String sql = "DELETE FROM shopping_car WHERE user_id = :userId AND pokemon_id = :pokemonId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateAmountById(ShopCar shopCar, Pokemon pokemon) {

        String sql = "UPDATE shopping_car SET amount = :buyCnt * :price WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());
        map.put("price", pokemon.getPrice());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public ShopCar getTotal(User user) {

        String sql = "SELECT seq_no, user_id, pokemon_id, buy_cnt, amount = SUM(amount) FROM shopping_car WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());

        List<ShopCar> shopCarList = namedParameterJdbcTemplate.query(sql, map, new ShopCarRowMapper());

        if (shopCarList.size() > 0) {

            return shopCarList.get(0);
        }

        return null;
    }

    @Override
    public void updateBuyCntById(ShopCar shopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }
}
