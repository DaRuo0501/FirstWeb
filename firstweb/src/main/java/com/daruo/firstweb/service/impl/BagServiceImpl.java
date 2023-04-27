package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BagServiceImpl implements BagService {

    @Autowired
    private BagDao bagDao;

    @Override
    public void createBag(Integer userId, Integer pokemonId) {

        bagDao.createBag(userId, pokemonId);
    }
}
