package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillDao skillDao;

    @Override
    public List<TempSkill> getSkillByName(String skillName1, String skillName2, String skillName3, String skillName4) {

        return skillDao.getSkillByName(skillName1, skillName2, skillName3, skillName4);
    }
}
