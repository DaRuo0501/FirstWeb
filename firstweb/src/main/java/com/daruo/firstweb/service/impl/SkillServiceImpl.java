package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SkillServiceImpl implements SkillService {

    private final static Logger log = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private BagDao bagDao;

    @Transactional
    @Override
    public List<TempSkill> getSkillByName(TempUser tempUser, String pokemonName) {

        try {

            List<TempBag> tempBagList = bagDao.getBag(tempUser.getUserId());

            TempBag tempBag = tempBagList.get(0);

            String skillName1 = tempBag.getSkill1();
            String skillName2 = tempBag.getSkill2();
            String skillName3 = tempBag.getSkill3();
            String skillName4 = tempBag.getSkill4();

            List<TempSkill> tempSkillList = skillDao.getSkillByName(skillName1, skillName2, skillName3, skillName4);

            return tempSkillList;

        } catch (Exception e) {

            log.warn(e.toString());
        }

        return null;
    }
}
