package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.model.Pokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonCategoryRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(rs.getInt("category_id"));

        String categoryStr = rs.getString("category_name");
        PokemonCategory category = PokemonCategory.valueOf(categoryStr);
        pokemon.setCategory(category);

        return pokemon;
    }
}
