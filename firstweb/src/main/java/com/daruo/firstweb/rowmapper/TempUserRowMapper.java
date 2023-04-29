package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempUserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempUser tempUser = new TempUser();
        tempUser.setUserId(rs.getInt("user_id"));
        tempUser.setUserName(rs.getString("user_name"));
        tempUser.setPassword(rs.getString("password"));
        tempUser.setEmail(rs.getString("email"));
        tempUser.setUserImgUrl(rs.getString("user_image_url"));
        tempUser.setMoney(rs.getInt("money"));
        tempUser.setCreatedDate(rs.getTimestamp("created_date"));
        tempUser.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return tempUser;
    }
}
