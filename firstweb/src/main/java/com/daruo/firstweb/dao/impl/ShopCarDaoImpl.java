package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.rowmapper.TempShopCarListRowMapper;
import com.daruo.firstweb.rowmapper.TempShopCarRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShopCarDaoImpl implements ShopCarDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 查詢 購物車內的 所有商品
    @Override
    public List<TempShopCar> getShopCarList(Integer userId) {

        String sql = "SELECT * FROM pokemon poke, shopping_car sc " +
                "WHERE poke.pokemon_id = sc.pokemon_id " +
                "AND user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempShopCar> shopCarList = namedParameterJdbcTemplate.query(sql, map, new TempShopCarListRowMapper());

        return shopCarList;
    }

    // 查詢 購物車內的 單筆商品
    @Override
    public TempShopCar getBuyCnt(TempShopCar tempShopCar) {

        String sql = "SELECT seq_no, user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "order_id, price, stock, buy_cnt, amount FROM shopping_car " +
                "WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", tempShopCar.getUserId());
        map.put("pokemonId", tempShopCar.getPokemonId());

        List<TempShopCar> tempShopCarList = namedParameterJdbcTemplate.query(sql, map, new TempShopCarRowMapper());

        if (tempShopCarList.size() > 0) {
            return tempShopCarList.get(0);
        } else {
            return null;
        }
    }

    // 購買數量 +1
    @Override
    public void addCount(TempShopCar tempShopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt + 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", tempShopCar.getUserId());
        map.put("pokemonId", tempShopCar.getPokemonId());
        map.put("buyCnt", tempShopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 購買數量 -1
    @Override
    public void reduceCount(TempShopCar tempShopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt - 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", tempShopCar.getUserId());
        map.put("pokemonId", tempShopCar.getPokemonId());
        map.put("buyCnt", tempShopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 刪除 購物車 內的 單筆 商品
    @Override
    public void deletePokemonById(TempShopCar tempShopCar) {

        String sql = "DELETE FROM shopping_car WHERE user_id = :userId AND pokemon_id = :pokemonId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", tempShopCar.getUserId());
        map.put("pokemonId", tempShopCar.getPokemonId());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 更新 購買金額 使其與 購買數量 相符合
    @Override
    public void updateAmountById(TempShopCar tempShopCar, TempPokemon tempPokemon) {

        String sql = "UPDATE shopping_car SET amount = :buyCnt * :price WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", tempShopCar.getUserId());
        map.put("pokemonId", tempShopCar.getPokemonId());
        map.put("buyCnt", tempShopCar.getBuyCnt());
        map.put("price", tempPokemon.getPrice());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 更改 購買的 數量
    @Override
    public void updateBuyCntById(TempShopCar tempShopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", tempShopCar.getUserId());
        map.put("pokemonId", tempShopCar.getPokemonId());
        map.put("buyCnt", tempShopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 添加 商品 至 購物車
    @Override
    public void addShopCar(TempUser tempUser, TempPokemon tempPokemon, TempShopCar tempShopCar) {

        String sql = "INSERT INTO shopping_car(user_id, seq_no, pokemon_id, pokemon_name, " +
                "pokemon_image_url, order_id, price, stock, buy_cnt, amount) " +
                "VALUES (:userId, :seqNo, :pokemonId, :pokemonName, " +
                ":pokemonImageUel, :orderId, :price, :stock, 1, :amount);";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", tempUser.getUserId());
        map.put("seqNo", tempShopCar.getSeqNo() + 1);
        map.put("pokemonId", tempPokemon.getPokemonId());
        map.put("pokemonName", tempPokemon.getPokemonName());
        map.put("pokemonImageUel", tempPokemon.getImageUrl());
        map.put("orderId", tempShopCar.getOrderId());
        map.put("price", tempPokemon.getPrice());
        map.put("stock", tempPokemon.getStock());
        map.put("amount", tempPokemon.getPrice() * tempShopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql,  new MapSqlParameterSource(map));

    }

    // 查詢 使用者的購物車 單筆 商品資料
    @Override
    public TempShopCar getShopCarPokemonByPokemonId(Integer userId, Integer pokemonId) {

        String sql = "SELECT seq_no, user_id, pokemon_id, pokemon_name, pokemon_image_url, " +
                "order_id, price, stock, buy_cnt, amount FROM shopping_car " +
                "WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pokemonId", pokemonId);

        List<TempShopCar> tempShopCarList = namedParameterJdbcTemplate.query(sql, map, new TempShopCarRowMapper());

        if (tempShopCarList.size() > 0) {
            return tempShopCarList.get(0);
        } else {
            return null;
        }
    }

    // 添加 第一筆 商品至購物車
    @Override
    public void createFirstShopCar(TempUser tempUser, TempPokemon tempPokemon) {

        String sql = "INSERT INTO shopping_car(user_id, seq_no, pokemon_id, pokemon_name, " +
                "pokemon_image_url, order_id, price, stock, buy_cnt, amount) " +
                "VALUES (:userId, 1, :pokemonId, :pokemonName, :pokemonImageUel, 1, :price, :stock, 1, :amount);";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", tempUser.getUserId());
        map.put("pokemonId", tempPokemon.getPokemonId());
        map.put("pokemonName", tempPokemon.getPokemonName());
        map.put("pokemonImageUel", tempPokemon.getImageUrl());
        map.put("price", tempPokemon.getPrice());
        map.put("stock", tempPokemon.getStock());
        map.put("amount", tempPokemon.getPrice());

        namedParameterJdbcTemplate.update(sql,  new MapSqlParameterSource(map));

    }

    @Override
    public TempShopCar getShopCarLastPokemonByUserId(Integer userId) {

        String sql = "SELECT user_id, seq_no, pokemon_id, pokemon_name, pokemon_image_url, " +
                "order_id, price, stock, buy_cnt, amount " +
                "FROM shopping_car " +
                "WHERE user_id = :userId " +
                "ORDER BY seq_no DESC;";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempShopCar> tempShopCarList = namedParameterJdbcTemplate.query(sql, map, new TempShopCarRowMapper());

        if (tempShopCarList.size() > 0) {
            return tempShopCarList.get(0);
        } else {
            return null;
        }
    }
}
