package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempShopCar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempShopCarRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempShopCar tempShopCar = new TempShopCar();
        tempShopCar.setUserId(rs.getInt("user_id"));
        tempShopCar.setSeqNo(rs.getInt("seq_no"));
        tempShopCar.setPokemonId(rs.getInt("pokemon_id"));
        tempShopCar.setPokemonName(rs.getString("pokemon_name"));
        tempShopCar.setImageUel(rs.getString("pokemon_image_url"));
        tempShopCar.setOrderId(rs.getInt("order_id"));
        tempShopCar.setPrice(rs.getInt("price"));
        tempShopCar.setStock(rs.getInt("stock"));
        tempShopCar.setBuyCnt(rs.getInt("buy_cnt"));
        tempShopCar.setAmount(rs.getInt("amount"));

        return tempShopCar;
    }
}
