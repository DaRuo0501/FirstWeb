package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface PokemonService {

    List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<Pokemon> getCategory();

    List<Integer> getPokemonsPage(PokemonQueryParams pokemonQueryParams);

    Pokemon getPokemonById(Integer pokemonId);

    TempPokemon getTempPokemonById(Integer pokemonId);

    Pokemon createShopCarById(Pokemon pokemon, User userId);

    Integer getPokemonCategoryPage(PokemonQueryParams pokemonQueryParams);

    List<TempPokemon> removePokemonCount(Integer userId);
}
