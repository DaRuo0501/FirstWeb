package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PokemonServiceImpl implements PokemonService {

    private final static Logger log = LoggerFactory.getLogger(PokemonServiceImpl.class);

    @Autowired
    private PokemonDao pokemonDao;

    // 取得 全部商品
    @Override
    public List<Pokemon> getPokemons(PokemonQueryParams pokemonQueryParams) {

        return pokemonDao.getPokemons(pokemonQueryParams);
    }

    // 取得 屬性
    @Override
    public List<Pokemon> getCategory() {
        return pokemonDao.getCategory();
    }

    // 取得 商品 的總頁數
    @Override
    public List<Integer> getPokemonsPage(PokemonQueryParams pokemonQueryParams) {

        // 取得 資料庫中，寶可夢的總數
        Integer total = pokemonDao.getPokemonsCount(pokemonQueryParams);

        // 調用 共用的 頁數 計算方法
        Integer page = pageCount(pokemonQueryParams, total);

        // 將迴圈取得的頁數，存放於 List 裡面
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i <= page; i++) {
            integerList.add(i);
        }

        // 回傳 所有頁數
        return integerList;
    }

    @Override
    public Pokemon getPokemonById(Integer pokemonId) {
        return pokemonDao.getPokemonById(pokemonId);
    }

    @Override
    public TempPokemon getTempPokemonById(Integer pokemonId) {

        return pokemonDao.getTempPokemonById(pokemonId);
    }

    // 新增商品至購物車
    @Override
    public Pokemon createShopCarById(Pokemon pokemon, User user) {

        try {

            // 取得 當前使用者 購物車內的商品編號 與 前端傳入的商品編號 相同的資料
            ShopCar shopCar = pokemonDao.getShopCarPokemonByPokemonId(pokemon.getPokemonId(), user);

            // 取得 當前使用者的 購物車
            ShopCar tempShopCar = pokemonDao.getShopCarPokemonByUserId(user);

            // 確認是否有購物車
            if (tempShopCar != null) {

                // 確認購物車內 是否已經有相同的商品
                if (shopCar == null) {

                    // 購物車內無此商品，將商品加入購物車內
                    pokemonDao.createShopCar(pokemon, tempShopCar, user);

                    return pokemon;

                } else {

                    return null;
                }

            } else {

                // 無購物車 新建第一筆購物車
                pokemonDao.createFirstShopCar(pokemon, user);

                return pokemon;
            }


        } catch (Exception e) {

            log.warn(e.getMessage());
            return null;
        }
    }

    // 查詢 屬性 所擁有的頁數
    @Override
    public Integer getPokemonCategoryPage(PokemonQueryParams pokemonQueryParams) {
        Integer total = pokemonDao.getPokemonsCount(pokemonQueryParams);

        // 調用 共用的 頁數 計算方法
        Integer page = pageCount(pokemonQueryParams, total);

        return page;
    }

    // 共用的 頁數 計算方法
    private Integer pageCount(PokemonQueryParams pokemonQueryParams, Integer total) {

        // 每頁要顯示的數量: 12個
        Integer count = pokemonQueryParams.getLimit();

        // 總共會產生多少頁數，初始值為: 0
        Integer page = 0;

        if (total % count == 0) {
            page = total / count;
        } else {
            page = total / count + 1;
        }

        return page;
    }
}
