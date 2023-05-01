package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempSkill;

import java.util.List;

public interface SkillDao {
    List<TempSkill> getSkillByName(String skillName1, String skillName2, String skillName3, String skillName4);
}
