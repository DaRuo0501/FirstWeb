package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempBag;

import java.util.List;

public interface BagService {
    void createBag(Integer userId, Integer pokemonId, String pokemonName);

    List<TempBag> getBag(Integer userId);
}
