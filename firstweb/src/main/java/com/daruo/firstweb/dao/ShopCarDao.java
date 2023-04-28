package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface ShopCarDao {

    List<TempPokemon> getShopCarList(Integer userId);

    ShopCar getBuyCnt(ShopCar shopCar);

    void addCount(ShopCar shopCar);

    void reduceCount(ShopCar shopCar);

    void deletePokemonById(ShopCar shopCar);

    void updateAmountById(ShopCar shopCar, Pokemon pokemon);

    void updateBuyCntById(ShopCar shopCar);

    void removeShopCarByUserId(User user);

    void createShopCar(Pokemon pokemon, ShopCar shopCar, User user);

    ShopCar getShopCarPokemonByPokemonId(Integer pokemonId, User user);

    ShopCar getShopCarPokemonByUserId(User user);

    void createFirstShopCar(Pokemon pokemon, User user);
}
