package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ShopCarService {

    List<TempShopCar> getShopCarList(Integer userId);

    TempShopCar getBuyCnt(TempShopCar tempShopCar);

    void addCount(TempShopCar tempShopCar);

    void reduceCount(TempShopCar tempBuyCnt);

    void deletePokemonById(Integer userId, Integer pokemonId);

    void updateAmountById(TempShopCar tempShopCar, TempPokemon tempPokemon);

    void updateBuyCntById(TempShopCar tempShopCar);

    void createShopCar(TempUser tempUser, Integer pokemonId, HttpSession session);
}
