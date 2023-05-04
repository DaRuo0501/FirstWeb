package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;

import java.util.List;

public interface PokemonService {

    List<TempPokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<TempPokemon> getCategory();

    List<Integer> getPokemonsPage(PokemonQueryParams pokemonQueryParams);

    TempPokemon getPokemonById(Integer pokemonId);

    TempPokemon getTempPokemonById(Integer pokemonId);

    Integer getPokemonCategoryPage(PokemonQueryParams pokemonQueryParams);
}
