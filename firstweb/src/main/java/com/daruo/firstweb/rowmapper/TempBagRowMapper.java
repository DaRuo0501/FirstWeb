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
        tempBag.setMyPkId(rs.getInt("my_pk_id"));
        tempBag.setCreatedDate(rs.getTimestamp("created_date"));
        tempBag.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return tempBag;
    }
}
