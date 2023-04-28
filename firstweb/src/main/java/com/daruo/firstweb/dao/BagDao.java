package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempBag;

import java.util.List;

public interface BagDao {
    void createBag(Integer userId, Integer pokemonId, String pokemonName);

    List<TempBag> getBag(Integer userId);
}
