package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempPokemon;

import java.util.List;

public interface PokemonDao {

    List<TempPokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<TempPokemon> getCategory();

    Integer getPokemonsCount(PokemonQueryParams pokemonQueryParams);

    TempPokemon getPokemonById(Integer pokemonId);

    TempPokemon getTempPokemonById(Integer pokemonId);

    void updatePokemonCountById(Integer pokemonId, Integer stock);

    void createUserPokemonValue(Integer myPkId, Integer userId, TempPokemon tempPokemon);

    Integer getMyPkLastId(Integer userId);

    void deleteById(Integer userId, Integer myPkId);

    List<TempPokemon> getPokemonByUserId(Integer userId);
}
