package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempBox;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempBoxRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempBox tempBox = new TempBox();
        tempBox.setBoxId(rs.getInt("box_id"));
        tempBox.setUserId(rs.getInt("user_id"));
        tempBox.setPokemonId(rs.getInt("pokemon_id"));
        tempBox.setPokemonName(rs.getString("pokemon_name"));
        tempBox.setPokemonImageUrl(rs.getString("pokemon_image_url"));

        String categoryStr = rs.getString("category");
        PokemonCategory category = PokemonCategory.valueOf(categoryStr);
        tempBox.setCategory(category);

        tempBox.setHp(rs.getInt("hp"));
        tempBox.setLv(rs.getInt("lv"));
        tempBox.setExp(rs.getInt("exp"));
        tempBox.setAttack(rs.getInt("attack"));
        tempBox.setDefense(rs.getInt("defense"));
        tempBox.setSpeed(rs.getInt("speed"));
        tempBox.setPrice(rs.getInt("price"));
        tempBox.setDescription(rs.getString("description"));
        tempBox.setCreatedDate(rs.getTimestamp("created_date"));
        tempBox.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return tempBox;
    }
}
