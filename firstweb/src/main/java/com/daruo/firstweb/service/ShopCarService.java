package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface ShopCarService {

    List<TempPokemon> getShopCarList(User user);

    ShopCar getBuyCnt(ShopCar shopCar);

    void addCount(ShopCar shopCar);

    void reduceCount(ShopCar tempBuyCnt);

    void deletePokemonById(ShopCar shopCar);

    void updateAmount(ShopCar shopCar, Pokemon pokemon);

    ShopCar getTotal(User user);
}
