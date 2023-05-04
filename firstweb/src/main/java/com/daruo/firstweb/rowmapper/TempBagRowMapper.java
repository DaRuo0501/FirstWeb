package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.TempBag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempBagRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempBag tempBag = new TempBag();
        tempBag.setBagId(rs.getInt("bag_id"));
        tempBag.setUserId(rs.getInt("user_id"));
        tempBag.setPokemonId(rs.getInt("pokemon_id"));
        tempBag.setPokemonName(rs.getString("pokemon_name"));
        tempBag.setPokemonImageUrl(rs.getString("pokemon_image_url"));

        String categoryStr = rs.getString("category");
        PokemonCategory category = PokemonCategory.valueOf(categoryStr);
        tempBag.setCategory(category);

        tempBag.setHp(rs.getInt("hp"));
        tempBag.setLv(rs.getInt("lv"));
        tempBag.setExp(rs.getInt("exp"));
        tempBag.setAttack(rs.getInt("attack"));
        tempBag.setDefense(rs.getInt("defense"));
        tempBag.setSpeed(rs.getInt("speed"));
        tempBag.setPrice(rs.getInt("price"));
        tempBag.setDescription(rs.getString("description"));
        tempBag.setCreatedDate(rs.getTimestamp("created_date"));
        tempBag.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return tempBag;
    }
}
