package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.PokemonRequest;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface PokemonDao {

    List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<Pokemon> getCategory();

    Integer getPokemonsCount(PokemonQueryParams pokemonQueryParams);

    Pokemon getPokemonById(Integer pokemonId);

    void createShopCar(Pokemon pokemon, User user);
}
