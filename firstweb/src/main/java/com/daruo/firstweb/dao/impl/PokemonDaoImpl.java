package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.rowmapper.PokemonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Pokemon> getAll() {

        String sql = "SELECT pokemon_id, pokemon_name, image_url, category, life, exp, attack, skill_1, skill_2, skill_3, skill_4, created_date, last_modified_date FROM pokemon;";

        Map<String, Object> map = new HashMap<>();

        List<Pokemon> pokemonList = namedParameterJdbcTemplate.query(sql, map, new PokemonRowMapper());

        return pokemonList;
    }
}
