package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;

import java.util.List;

public interface BagService {
    void createBag(Integer userId, TempBag tempBag);

    List<TempBag> getBag(Integer userId);

    TempPokemon getPokemonById(Integer userId, Integer pokemonId);
}
