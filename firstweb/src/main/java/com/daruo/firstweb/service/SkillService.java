package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.dto.TempUser;

import java.util.List;

public interface SkillService {

    List<TempSkill> getSkillByPokemonId(Integer bagId);

    void remove(Integer userId,Integer bagId, Integer skillId);
}
