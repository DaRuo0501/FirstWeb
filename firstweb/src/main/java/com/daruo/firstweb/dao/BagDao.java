package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;

import java.util.List;

public interface BagDao {

    void createBag(Integer userId, TempPokemon tempPokemon, Integer bagId);

    List<TempBag> getBag(Integer userId);

    Integer getLastBagIdByUserId(Integer userId);

    void deleteBagId(Integer userId, Integer bagId);

    List<TempBag> getBags(Integer userId, Integer bagId);

    void updateBagId(Integer userId, Integer bagId, Integer newBagId);

    TempBag getBagById(Integer userId, Integer bagId);
}
