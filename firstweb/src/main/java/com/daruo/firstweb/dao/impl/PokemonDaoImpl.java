package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.rowmapper.PokemonCategoryRowMapper;
import com.daruo.firstweb.rowmapper.PokemonRowMapper;
import com.daruo.firstweb.rowmapper.ShopCarRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PokemonDaoImpl implements PokemonDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams) {

        String sql = "SELECT pokemon_id, pokemon_name, image_url," +
                " category, life, exp, attack, price, stock," +
                " skill_1, skill_2, skill_3, skill_4, created_date, last_modified_date" +
                " FROM pokemon WHERE 1 = 1";

        Map<String, Object> map = new HashMap<>();
        map.put("priceMin", pokemonQueryParams.getPriceMin());
        map.put("priceMax", pokemonQueryParams.getPriceMax());
        map.put("limit", pokemonQueryParams.getLimit());
        map.put("offset", pokemonQueryParams.getOffset());

        // 查詢條件
        sql = addFilteringSql(sql, map, pokemonQueryParams);

        // 價格
        sql += " AND price BETWEEN :priceMin AND :priceMax";

        // 排序
        sql += " ORDER BY " + pokemonQueryParams.getOrderBy() + " " + pokemonQueryParams.getSort();

        // 分頁
        sql += " LIMIT :limit OFFSET :offset";

        List<Pokemon> pokemonList = namedParameterJdbcTemplate.query(sql, map, new PokemonRowMapper());

        return pokemonList;
    }

    // 查詢所有屬性
    @Override
    public List<Pokemon> getCategory() {

        String sql = "SELECT category_id, category_name from category";

        Map<String, Object> map = new HashMap<>();

        List<Pokemon> pokemonList = namedParameterJdbcTemplate.query(sql, map, new PokemonCategoryRowMapper());

        return pokemonList;
    }

    // 查詢商品的數量
    @Override
    public Integer getPokemonsCount(PokemonQueryParams pokemonQueryParams) {

        String sql = "SELECT count(pokemon_id) FROM pokemon WHERE 1 = 1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, pokemonQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    // 依照編號查詢單筆商品
    @Override
    public Pokemon getPokemonById(Integer pokemonId) {

        String sql = "SELECT pokemon_id, pokemon_name, image_url," +
                " category, life, exp, attack, price, stock," +
                " skill_1, skill_2, skill_3, skill_4, created_date, last_modified_date" +
                " FROM pokemon WHERE pokemon_id = :pokemonId";

        Map<String, Object> map = new HashMap<>();
        map.put("pokemonId", pokemonId);

        List<Pokemon> pokemonList = namedParameterJdbcTemplate.query(sql, map , new PokemonRowMapper());

        if (pokemonList.size() > 0) {
            return pokemonList.get(0);
        } else {
            return null;
        }
    }

    // 建立購物車
    @Override
    public void createShopCar(Pokemon pokemon, User user) {

        String sql = "INSERT INTO shopping_car(user_id, pokemon_id, buy_cnt) VALUES (:userId, :pokemonId, 1);";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("pokemonId", pokemon.getPokemonId());

        namedParameterJdbcTemplate.update(sql,  new MapSqlParameterSource(map));

    }

    @Override
    public ShopCar getShopCarPokemonByUserId(Integer pokemonId, User user) {

        String sql = "SELECT seq_no, user_id, pokemon_id, buy_cnt FROM shopping_car WHERE user_id = :userId AND pokemon_id = :pokemonId;";

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

    // 更新購物車
    @Override
    public void addShopCarPokemonCount(Pokemon pokemon, User user) {

        String sql = "UPDATE shopping_car SET buy_cnt = buy_cnt + 1 WHERE user_id = :userId AND pokemon_id = :pokemonId;";

        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("pokemonId", pokemon.getPokemonId());

        namedParameterJdbcTemplate.update(sql, map);

    }

    // 共用查詢條件
    private String addFilteringSql(String sql,
                                   Map<String, Object> map,
                                   PokemonQueryParams pokemonQueryParams) {

        if (pokemonQueryParams.getPokemonCategory() != null) {

            sql += " AND category LIKE :category";
            map.put("category", pokemonQueryParams.getPokemonCategory().name());

        } else if (pokemonQueryParams.getSearch() != null) {

            // 查詢 名稱
            sql += " AND pokemon_name LIKE :search";

            // 查詢 編號
            sql += " OR pokemon_id LIKE :search";

            // 查詢 屬性
            sql += " OR category LIKE :search";

            // 查詢 技能
            sql += " OR skill_1 LIKE :search" +
                    " OR skill_2 LIKE :search" +
                    " OR skill_3 LIKE :search" +
                    " OR skill_4 LIKE :search";

            map.put("search", "%" + pokemonQueryParams.getSearch() + "%");

        }

        return sql;
    }
}
