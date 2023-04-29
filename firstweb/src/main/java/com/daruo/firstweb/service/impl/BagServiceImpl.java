package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BagServiceImpl implements BagService {

    @Autowired
    private BagDao bagDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Override
    public void createBag(Integer userId, TempBag tempBag) {

        bagDao.createBag(userId, tempBag);
    }

    @Override
    public List<TempBag> getBag(Integer userId) {

        return bagDao.getBag(userId);
    }

    @Override
    public TempPokemon getPokemonById(Integer userId, Integer pokemonId) {

        return null;
    }
}
