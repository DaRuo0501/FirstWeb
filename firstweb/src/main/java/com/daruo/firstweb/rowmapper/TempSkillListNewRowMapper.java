package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.dto.TempSkill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempSkillListNewRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempSkill tempSkill = new TempSkill();
        tempSkill.setPokemonId(rs.getInt("pokemon_id"));
        tempSkill.setSkillId(rs.getInt("skill_id"));
        tempSkill.setSkillName(rs.getString("skill_name"));
        tempSkill.setSkillAttack(rs.getInt("skill_attack"));
        tempSkill.setSkillCategoryUrl(rs.getString("skill_category_url"));

        return tempSkill;
    }
}
