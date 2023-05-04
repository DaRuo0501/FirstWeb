package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.SkillCategory;
import com.daruo.firstweb.dto.TempSkill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempSkillListRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempSkill tempSkill = new TempSkill();
        tempSkill.setSkillId(rs.getInt("skill_id"));
        tempSkill.setSkillName(rs.getString("skill_name"));

        String categoryStr = rs.getString("skill_category");
        SkillCategory category = SkillCategory.valueOf(categoryStr);
        tempSkill.setSkillCategory(category);

        tempSkill.setSkillCategoryUrl(rs.getString("skill_category_url"));
        tempSkill.setSkillAttack(rs.getInt("skill_attack"));
        tempSkill.setSkillDescription(rs.getString("skill_description"));

        tempSkill.setPokemonId(rs.getInt("pokemon_id"));
        tempSkill.setPokemonName(rs.getString("pokemon_name"));

        return tempSkill;
    }
}
