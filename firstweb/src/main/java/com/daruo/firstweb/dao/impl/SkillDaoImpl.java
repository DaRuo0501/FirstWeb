package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.rowmapper.TempSkillListRowMapper;
import com.daruo.firstweb.rowmapper.TempSkillRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SkillDaoImpl implements SkillDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TempSkill> getSkillByPokemonId(Integer pokemonId) {

        String sql = "SELECT ps.user_id, p.pokemon_id, p.pokemon_name, " +
                "s.skill_id, s.skill_name, s.skill_attack, s.skill_category, s.skill_category_url, s.skill_description " +
                "FROM pokemon p " +
                "join pokemon_skill ps on p.pokemon_id = ps.pokemon_id " +
                "join skill s on ps.skill_id = s.skill_id " +
                "join category c on c.category_name = p.category " +
                "WHERE p.pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("pokemonId", pokemonId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillListRowMapper());

        return tempSkillList;
    }
}
