package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface PokemonDao {

    List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<Pokemon> getCategory();

    Integer getPokemonsCount(PokemonQueryParams pokemonQueryParams);

    Pokemon getPokemonById(Integer pokemonId);

    TempPokemon getTempPokemonById(Integer pokemonId);

    void createShopCar(Pokemon pokemon, ShopCar shopCar, User user);

    ShopCar getShopCarPokemonByPokemonId(Integer pokemonId, User user);

    ShopCar getShopCarPokemonByUserId(User user);

    void addShopCarPokemonCount(ShopCar shopCar);

    void createFirstShopCar(Pokemon pokemon, User user);
}
