package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BagServiceImpl implements BagService {

    @Autowired
    private BagDao bagDao;

    @Override
    public void createBag(Integer userId, Integer pokemonId, String pokemonName) {

        bagDao.createBag(userId, pokemonId, pokemonName);
    }

    @Override
    public List<TempBag> getBag(Integer userId) {

        return bagDao.getBag(userId);
    }
}
