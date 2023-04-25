package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface ShopCarDao {

    List<TempPokemon> getShopCarList(User user);

    ShopCar getBuyCnt(ShopCar shopCar);

    void addCount(ShopCar shopCar);

    void reduceCount(ShopCar shopCar);
}
