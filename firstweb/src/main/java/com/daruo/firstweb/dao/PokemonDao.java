package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.model.Pokemon;

import java.util.List;

public interface PokemonDao {

    List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams);

    List<Pokemon> getCategory();
}
