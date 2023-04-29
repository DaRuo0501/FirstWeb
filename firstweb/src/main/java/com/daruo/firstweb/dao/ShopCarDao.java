package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;

import java.util.List;

public interface ShopCarDao {

    List<TempShopCar> getShopCarList(Integer userId);

    TempShopCar getBuyCnt(TempShopCar tempShopCar);

    void addCount(TempShopCar tempShopCar);

    void reduceCount(TempShopCar tempShopCar);

    void deletePokemonById(TempShopCar tempShopCar);

    void updateAmountById(TempShopCar tempShopCar, TempPokemon tempPokemon);

    void updateBuyCntById(TempShopCar tempShopCar);

    TempShopCar getShopCarPokemonByPokemonId(Integer userId, Integer pokemonId);

    void createFirstShopCar(TempUser tempUser, TempPokemon tempPokemon);

    void addShopCar(TempUser tempUser, TempPokemon tempPokemon, TempShopCar tempShopCar);

    TempShopCar getShopCarLastPokemonByUserId(Integer userId);
}
