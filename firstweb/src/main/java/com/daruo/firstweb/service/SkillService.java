package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempSkill;

import java.util.List;

public interface SkillService {
    List<TempSkill> getSkillByName(String skillName1, String skillName2, String skillName3, String skillName4);
}
