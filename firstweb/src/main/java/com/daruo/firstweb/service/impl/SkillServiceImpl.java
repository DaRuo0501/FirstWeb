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

    @Override
    public List<TempSkill> getSkillByPokemonId(Integer pokemonId) {

        return skillDao.getSkillByPokemonId(pokemonId);
    }
}
