package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.dto.TempUser;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SkillService {

    List<TempSkill> getSkillByPokemonId(Integer bagId);

    void remove(Integer userId,Integer bagId, Integer skillId);

    List<TempSkill> getSkillByMyPkId(Integer myPkId);

    List<TempSkill> getPokemonNewSkill(TempBag tempBag);

    void add(Integer userId, Integer bagId, Integer skillId, HttpSession session);
}
