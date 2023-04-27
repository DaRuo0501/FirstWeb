package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempOrderRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempOrder tempOrder = new TempOrder();
        tempOrder.setOrderId(rs.getInt("order_id"));
        tempOrder.setUserId(rs.getInt("user_id"));
        tempOrder.setTotalAmount(rs.getInt("total_amount"));
        tempOrder.setCreatedDate(rs.getTimestamp("created_date"));
        tempOrder.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return tempOrder;
    }
}
