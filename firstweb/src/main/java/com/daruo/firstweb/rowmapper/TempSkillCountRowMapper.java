package com.daruo.firstweb.rowmapper;

import com.daruo.firstweb.constant.SkillCategory;
import com.daruo.firstweb.dto.TempSkill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempSkillCountRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        TempSkill tempSkill = new TempSkill();
        tempSkill.setSkillId(rs.getInt("skill_id"));
        tempSkill.setSkillName(rs.getString("skill_name"));

        return tempSkill;
    }
}
