package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempSkill;
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
    public List<TempSkill> getSkillByName(String skillName1, String skillName2, String skillName3, String skillName4) {

        String sql = "SELECT * FROM skill WHERE skill_name = :skillName1 OR skill_name = :skillName2 OR skill_name = :skillName3 OR skill_name = :skillName4;";

        Map<String, Object> map = new HashMap<>();
        map.put("skillName1", skillName1);
        map.put("skillName2", skillName2);
        map.put("skillName3", skillName3);
        map.put("skillName4", skillName4);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillRowMapper());

        return tempSkillList;
    }
}
