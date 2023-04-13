package com.daruo.firstweb.dao;

import com.daruo.firstweb.model.Pokemon;

import java.util.List;

public interface PokemonDao {
    List<Pokemon> getAll();
}
