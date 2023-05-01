package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.TempPokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempPokemonRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        TempPokemon tempPokemon = new TempPokemon();

        tempPokemon.setPokemonId(resultSet.getInt("pokemon_id"));
        tempPokemon.setPokemonName(resultSet.getString("pokemon_name"));
        tempPokemon.setPokemonImageUrl(resultSet.getString("pokemon_image_url"));

        String categoryStr = resultSet.getString("category");
        PokemonCategory category = PokemonCategory.valueOf(categoryStr);
        tempPokemon.setCategory(category);

        tempPokemon.setHp(resultSet.getInt("hp"));
        tempPokemon.setLv(resultSet.getInt("lv"));
        tempPokemon.setExp(resultSet.getInt("exp"));
        tempPokemon.setAttack(resultSet.getInt("attack"));
        tempPokemon.setDefense(resultSet.getInt("defense"));
        tempPokemon.setSpeed(resultSet.getInt("speed"));
        tempPokemon.setPrice(resultSet.getInt("price"));
        tempPokemon.setStock(resultSet.getInt("stock"));
        tempPokemon.setDescription(resultSet.getString("description"));
        tempPokemon.setSkill1(resultSet.getString("skill_1"));
        tempPokemon.setSkill2(resultSet.getString("skill_2"));
        tempPokemon.setSkill3(resultSet.getString("skill_3"));
        tempPokemon.setSkill4(resultSet.getString("skill_4"));
        tempPokemon.setCreatedDate(resultSet.getTimestamp("created_date"));
        tempPokemon.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return tempPokemon;
    }
}
