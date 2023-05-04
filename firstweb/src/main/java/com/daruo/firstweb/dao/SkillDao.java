package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempSkill;

import java.util.List;

public interface SkillDao {

    List<TempSkill> getSkillByPokemonId(Integer pokemonId);
}
