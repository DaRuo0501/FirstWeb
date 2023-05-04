package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.model.Pokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonRowMapper implements RowMapper<Pokemon> {
    @Override
    public Pokemon mapRow(ResultSet resultSet, int i) throws SQLException {

        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(resultSet.getInt("pokemon_id"));
        pokemon.setPokemonName(resultSet.getString("pokemon_name"));
        pokemon.setPokemonImageUrl(resultSet.getString("pokemon_image_url"));

        String categoryStr = resultSet.getString("category");
        PokemonCategory category = PokemonCategory.valueOf(categoryStr);
        pokemon.setCategory(category);

        pokemon.setHp(resultSet.getInt("hp"));
        pokemon.setLv(resultSet.getInt("lv"));
        pokemon.setExp(resultSet.getInt("exp"));
        pokemon.setAttack(resultSet.getInt("attack"));
        pokemon.setDefense(resultSet.getInt("defense"));
        pokemon.setSpeed(resultSet.getInt("speed"));
        pokemon.setPrice(resultSet.getInt("price"));
        pokemon.setStock(resultSet.getInt("stock"));
        pokemon.setCreatedDate(resultSet.getTimestamp("created_date"));
        pokemon.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return pokemon;
    }
}
