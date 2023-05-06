package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.rowmapper.MyPokemonLastIdRowMapper;
import com.daruo.firstweb.rowmapper.PokemonCategoryRowMapper;
import com.daruo.firstweb.rowmapper.TempPokemonRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PokemonDaoImpl implements PokemonDao {

    private final static Logger log = LoggerFactory.getLogger(PokemonDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TempPokemon> getPokemons(PokemonQueryParams pokemonQueryParams) {

        String sql = "SELECT pokemon_id, pokemon_name, pokemon_image_url," +
                " category, hp, lv, exp, attack, price, stock, defense, speed, description," +
                " created_date, last_modified_date" +
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

        List<TempPokemon> tempPokemonList = namedParameterJdbcTemplate.query(sql, map, new TempPokemonRowMapper());

        return tempPokemonList;
    }

    // 查詢所有屬性
    @Override
    public List<TempPokemon> getCategory() {

        String sql = "SELECT category_id, category_name from category";

        Map<String, Object> map = new HashMap<>();

        List<TempPokemon> tempPokemonList = namedParameterJdbcTemplate.query(sql, map, new PokemonCategoryRowMapper());

        return tempPokemonList;
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
    public TempPokemon getPokemonById(Integer pokemonId) {

        String sql = "SELECT pokemon_id, pokemon_name, pokemon_image_url," +
                " category, hp, lv, exp, attack, defense, speed, price, stock, description," +
                " created_date, last_modified_date" +
                " FROM pokemon WHERE pokemon_id = :pokemonId";

        Map<String, Object> map = new HashMap<>();
        map.put("pokemonId", pokemonId);

        List<TempPokemon> tempPokemonList = namedParameterJdbcTemplate.query(sql, map , new TempPokemonRowMapper());

        if (tempPokemonList.size() > 0) {
            return tempPokemonList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public TempPokemon getTempPokemonById(Integer pokemonId) {

        String sql = "SELECT pokemon_id, pokemon_name, pokemon_image_url," +
                " category, hp, lv, exp, attack, defense, speed, price, stock, description," +
                " created_date, last_modified_date" +
                " FROM pokemon WHERE pokemon_id = :pokemonId";

        Map<String, Object> map = new HashMap<>();
        map.put("pokemonId", pokemonId);

        List<TempPokemon> tempPokemonList = namedParameterJdbcTemplate.query(sql, map , new TempPokemonRowMapper());

        if (tempPokemonList.size() > 0) {
            return tempPokemonList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updatePokemonCountById(Integer pokemonId, Integer stock) {

        String sql = "UPDATE pokemon SET stock = :stock WHERE pokemon_id = :pokemonId;";

        Map<String,Object> map = new HashMap<>();
        map.put("pokemonId", pokemonId);
        map.put("stock", stock);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public Integer getMyPkLastId(Integer userId) {

        try {

            String sql = "SELECT mp.my_pk_id FROM my_pokemon_value mp WHERE user_id = :userId ORDER BY my_pk_id DESC;";

            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);

            List<TempPokemon> tempPokemonList = namedParameterJdbcTemplate.query(sql, map, new MyPokemonLastIdRowMapper());

            if (tempPokemonList.size() > 0) {

                return tempPokemonList.get(0).getMyPokemonId() + 1;
            }

            return 1;

        } catch (RuntimeException e) {

            log.error(e.toString());
        }

        return null;
    }

    @Override
    public void deleteById(Integer userId, Integer myPkId) {

        String sql = "DELETE FROM my_pokemon_value WHERE my_pk_id = :myPkId AND user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("myPkId", myPkId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void createUserPokemon(Integer myPkId, Integer userId, TempPokemon tempPokemon) {

        String sql = "INSERT INTO my_pokemon_value (my_pk_id, user_id, pokemon_id, pokemon_name, pokemon_image_url," +
                " category, hp, lv, exp, attack, defense, speed, price," +
                " `description`, created_date, last_modified_date)" +
                " VALUES (:myPkId, :userId, :pokemonId, :pokemonName, :pokemonImageUrl," +
                " :category, :hp, :lv, :exp, :attack, :defense, :speed, :price," +
                " :description, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("myPkId", myPkId);
        map.put("userId",userId);
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

            map.put("search", "%" + pokemonQueryParams.getSearch() + "%");

        }

        return sql;
    }
}
