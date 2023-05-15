package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempSkill;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SkillService {

    List<TempSkill> getSkillByPokemonId(Integer bagId);

    void remove(Integer userId,Integer bagId, Integer skillId);

    List<TempSkill> getSkillByMyPkId(Integer myPkId, Integer userId);

    List<TempSkill> getPokemonNewSkill(TempBag tempBag);

    void add(Integer userId, Integer bagId, Integer skillId, HttpSession session);
}
