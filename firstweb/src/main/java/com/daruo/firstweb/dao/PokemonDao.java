package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;

import java.util.List;

public interface PokemonDao {

    List<TempPokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<TempPokemon> getCategory();

    Integer getPokemonsCount(PokemonQueryParams pokemonQueryParams);

    TempPokemon getPokemonById(Integer pokemonId);

    TempPokemon getTempPokemonById(Integer pokemonId);

    void updatePokemonCountById(Integer pokemonId, Integer stock);
}
