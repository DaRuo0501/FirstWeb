package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import com.daruo.firstweb.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private ShopCarDao shopCarDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Override
    public List<TempPokemon> getShopCarList(User user) {

        return shopCarDao.getShopCarList(user);
    }

    @Override
    public ShopCar getBuyCnt(ShopCar shopCar) {
        return shopCarDao.getBuyCnt(shopCar);
    }

    @Override
    public void addCount(ShopCar shopCar) {

        shopCarDao.addCount(shopCar);
    }

    @Override
    public void reduceCount(ShopCar shopCar) {

        shopCarDao.reduceCount(shopCar);
    }

    @Override
    public void deletePokemonById(ShopCar shopCar) {

        shopCarDao.deletePokemonById(shopCar);
    }

    @Override
    public void updateAmountById(ShopCar shopCar, Pokemon pokemon) {

        shopCarDao.updateAmountById(shopCar, pokemon);
    }

    @Override
    public void updateBuyCntById(ShopCar shopCar) {

        shopCarDao.updateBuyCntById(shopCar);
    }

    @Override
    public void removeShopCarByUserId(User user) {

        shopCarDao.removeShopCarByUserId(user);
    }

    @Override
    public void removePokemonCount(User user) {

        // 取得要扣除數量的 商品
        List<TempPokemon> shopCarList = shopCarDao.getShopCarList(user);

        for (TempPokemon tp : shopCarList) {

            // 取得貨架上的商品數量
            Pokemon tpPokemon = pokemonDao.getPokemonById(tp.getPokemonId());

            // 更新貨架上的 商品數量 = 貨架上的數量 - 購買數量
            pokemonDao.updatePokemonCountById(tpPokemon.getStock() - tp.getBuyCnt(), tpPokemon.getPokemonId());
        }
    }
}
