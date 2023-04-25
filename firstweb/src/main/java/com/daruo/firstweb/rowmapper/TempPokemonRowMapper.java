package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempPokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempPokemonRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        TempPokemon tempPokemon = new TempPokemon();

        tempPokemon.setPokemonId(resultSet.getInt("pokemon_id"));
        tempPokemon.setPrice(resultSet.getInt("price"));
        tempPokemon.setStock(resultSet.getInt("stock"));
        tempPokemon.setAmount(resultSet.getInt("amount"));

        return tempPokemon;
    }
}
