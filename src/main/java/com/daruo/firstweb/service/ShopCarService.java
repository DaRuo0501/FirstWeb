package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface ShopCarService {
    List<TempPokemon> getShopCarList(User user);
}
