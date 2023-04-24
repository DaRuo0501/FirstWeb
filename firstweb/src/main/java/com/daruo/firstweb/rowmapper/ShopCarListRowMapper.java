package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempPokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopCarListRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempPokemon tempPokemon = new TempPokemon();
        tempPokemon.setPokemonId(rs.getInt("pokemon_id"));
        tempPokemon.setPokemonName(rs.getString("pokemon_name"));
        tempPokemon.setImageUrl(rs.getString("image_url"));
        tempPokemon.setPrice(rs.getInt("price"));
        tempPokemon.setStock(rs.getInt("stock"));
        tempPokemon.setBuyCnt(rs.getInt("buy_cnt"));

        return tempPokemon;
    }
}
