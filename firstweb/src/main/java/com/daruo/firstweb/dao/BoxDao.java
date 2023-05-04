package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;

public interface BoxDao {

    void createBox(Integer userId, TempPokemon tempPokemon, Integer boxId);

    Integer getLastBoxIdByUserId(Integer userId);

    void createBoxByTempBag(TempBag tempBag, Integer boxId);
}
