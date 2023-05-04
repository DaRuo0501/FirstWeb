package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempSkill;

import java.util.List;

public interface BagService {

    List<TempBag> getBag(Integer userId);

    void deleteById(Integer userId, Integer bagId);

    void goToBoxById(Integer userId, Integer bagId);
}
