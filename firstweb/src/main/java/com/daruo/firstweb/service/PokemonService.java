package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.model.Pokemon;

import java.util.List;

public interface PokemonService {

    List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams);
}
