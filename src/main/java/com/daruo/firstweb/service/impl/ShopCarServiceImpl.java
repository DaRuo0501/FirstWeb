package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private ShopCarDao shopCarDao;

    @Override
    public List<TempPokemon> getShopCarList(User user) {

        return shopCarDao.getShopCarList(user);
    }
}
