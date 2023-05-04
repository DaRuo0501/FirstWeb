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
    public List<TempSkill> getSkillByPokemonId(Integer userId, Integer pokemonId) {

        String sql = "SELECT b.bag_id, b.user_id, b.pokemon_id, b.pokemon_name," +
                " s.skill_id, s.skill_name, s.skill_category, s.skill_category_url, s.skill_attack, s.skill_description " +
                "FROM bag b " +
                "join pokemon_skill ps on b.pokemon_id = ps.pokemon_id " +
                "join skill s on ps.skill_id = s.skill_id " +
                "WHERE b.user_id = :userId AND b.pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pokemonId", pokemonId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillListRowMapper());

        return tempSkillList;
    }
}
