package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;

import java.util.List;

public interface BagDao {
    void createBag(Integer userId, TempBag tempBag);

    List<TempBag> getBag(Integer userId);
}
