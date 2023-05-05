package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempPokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyPokemonLastIdRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempPokemon tempPokemon = new TempPokemon();
        tempPokemon.setMyPokemonId(rs.getInt("my_Pk_Id"));

        return tempPokemon;
    }
}
