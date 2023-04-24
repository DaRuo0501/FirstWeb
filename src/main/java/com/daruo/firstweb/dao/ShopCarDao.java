package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface ShopCarDao {
    List<TempPokemon> getShopCarList(User user);
}
