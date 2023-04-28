package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.rowmapper.ShopCarListRowMapper;
import com.daruo.firstweb.rowmapper.ShopCarRowMapper;
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
    public List<TempPokemon> getShopCarList(Integer userId) {

        String sql = "SELECT sc.user_id, sc.buy_cnt, sc.amount, " +
                "poke.pokemon_id, poke.pokemon_name, poke.image_url, " +
                "poke.price, poke.stock " +
                "FROM pokemon poke, shopping_car sc " +
                "WHERE poke.pokemon_id = sc.pokemon_id " +
                "AND user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempPokemon> shopCarList = namedParameterJdbcTemplate.query(sql, map, new ShopCarListRowMapper());

        return shopCarList;
    }

    // 查詢 購物車內的 單筆商品
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

    // 購買數量 +1
    @Override
    public void addCount(ShopCar shopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt + 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 購買數量 -1
    @Override
    public void reduceCount(ShopCar shopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt - 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 刪除 購物車 內的 單筆 商品
    @Override
    public void deletePokemonById(ShopCar shopCar) {

        String sql = "DELETE FROM shopping_car WHERE user_id = :userId AND pokemon_id = :pokemonId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 更新 購買金額 使其與 購買數量 相符合
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

    // 更改 購買的 數量
    @Override
    public void updateBuyCntById(ShopCar shopCar) {

        String sql = "UPDATE shopping_car SET buy_cnt = :buyCnt WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", shopCar.getUserId());
        map.put("pokemonId", shopCar.getPokemonId());
        map.put("buyCnt", shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 刪除 購物車 內的 所有商品
    @Override
    public void removeShopCarByUserId(User user) {

        String sql = "DELETE FROM shopping_car WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 添加 商品 至 購物車
    @Override
    public void createShopCar(Pokemon pokemon, ShopCar shopCar, User user) {

        String sql = "INSERT INTO shopping_car(user_id, seq_no, pokemon_id, order_id, buy_cnt, amount) " +
                "VALUES (:userId, :seqNo, :pokemonId, :orderId, 1, :amount);";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("seqNo", shopCar.getSeqNo() + 1);
        map.put("pokemonId", pokemon.getPokemonId());
        map.put("orderId", 1);
        map.put("amount", pokemon.getPrice() * shopCar.getBuyCnt());

        namedParameterJdbcTemplate.update(sql,  new MapSqlParameterSource(map));

    }

    // 查詢 使用者的購物車 單筆 商品資料
    @Override
    public ShopCar getShopCarPokemonByPokemonId(Integer pokemonId, User user) {

        String sql = "SELECT seq_no, user_id, pokemon_id, order_id, buy_cnt, amount FROM shopping_car " +
                "WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("pokemonId", pokemonId);

        List<ShopCar> shopCarList = namedParameterJdbcTemplate.query(sql, map, new ShopCarRowMapper());

        if (shopCarList.size() > 0) {
            return shopCarList.get(0);
        } else {
            return null;
        }
    }

    // 查詢 購物車內 單筆 商品
    @Override
    public ShopCar getShopCarPokemonByUserId(User user) {

        String sql = "SELECT seq_no, user_id, pokemon_id, order_id, buy_cnt, amount " +
                "FROM shopping_car " +
                "WHERE user_id = :userId " +
                "ORDER BY seq_no DESC;";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserId());

        List<ShopCar> shopCarList = namedParameterJdbcTemplate.query(sql, map, new ShopCarRowMapper());

        if (shopCarList.size() > 0) {
            return shopCarList.get(0);
        } else {
            return null;
        }
    }

    // 添加 第一筆 商品至購物車
    @Override
    public void createFirstShopCar(Pokemon pokemon, User user) {

        String sql = "INSERT INTO shopping_car(user_id, seq_no, pokemon_id, order_id, buy_cnt, amount) " +
                "VALUES (:userId, 1, :pokemonId, 1, 1, :amount);";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("pokemonId", pokemon.getPokemonId());
        map.put("amount", pokemon.getPrice());

        namedParameterJdbcTemplate.update(sql,  new MapSqlParameterSource(map));

    }
}
