package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.model.ShopCar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopCarRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        ShopCar shopCar = new ShopCar();
        shopCar.setSeqNo(rs.getInt("seq_no"));
        shopCar.setUserId(rs.getInt("user_id"));
        shopCar.setPokemonId(rs.getInt("pokemon_id"));
        shopCar.setBuyCnt(rs.getInt("buy_cnt"));
        shopCar.setAmount(rs.getInt("amount"));

        return shopCar;
    }
}