package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.TempBox;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LastBoxRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempBox tempBox = new TempBox();
        tempBox.setBoxId(rs.getInt("box_id"));
        tempBox.setUserId(rs.getInt("user_id"));
        tempBox.setMyPkId(rs.getInt("my_pk_id"));
        tempBox.setCreatedDate(rs.getTimestamp("created_date"));
        tempBox.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return tempBox;
    }
}
