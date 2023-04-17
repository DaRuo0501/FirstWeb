package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonDao pokemonDao;

    @Override
    public List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams) {

        return pokemonDao.getPokemons(pokemonQueryParams);
    }
}
