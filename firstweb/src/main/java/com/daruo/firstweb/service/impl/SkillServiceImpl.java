package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillServiceImpl implements SkillService {

    private final static Logger log = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private BagDao bagDao;

    @Override
    public List<TempSkill> getSkillByPokemonId(Integer bagId) {

        return skillDao.getSkillByPokemonId(bagId);
    }

    @Override
    public void remove(Integer userId,Integer bagId, Integer skillId) {

        TempBag tempBag = bagDao.getBagById(userId, bagId);

        skillDao.remove(tempBag.getUserId(), tempBag.getMyPkId(), skillId);
    }

    @Override
    public List<TempSkill> getSkillByMyPkId(Integer myPkId) {

        return skillDao.getSkillByMyPkId(myPkId);
    }

    @Override
    public List<TempSkill> getPokemonNewSkill(TempBag tempBag) {

        return skillDao.getPokemonNewSkill(tempBag);
    }

    @Override
    public void add(Integer userId, Integer bagId, Integer skillId) {

        TempBag tempBag = bagDao.getBagById(userId, bagId);

        TempSkill tempSkill = skillDao.getSkillName(skillId);

        skillDao.add(tempBag, tempSkill);
    }
}
