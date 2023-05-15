package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.ShopCarDao;
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

    @Autowired
    private ShopCarDao shopCarDao;

    // 取得 全部商品
    @Override
    public List<TempPokemon> getPokemons(PokemonQueryParams pokemonQueryParams) {

        return pokemonDao.getPokemons(pokemonQueryParams);
    }

    // 取得 屬性
    @Override
    public List<TempPokemon> getCategory() {

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
    public TempPokemon getPokemonById(Integer pokemonId) {
        return pokemonDao.getPokemonById(pokemonId);
    }

    @Override
    public TempPokemon getTempPokemonById(Integer pokemonId) {

        return pokemonDao.getTempPokemonById(pokemonId);
    }

    // 查詢 屬性 所擁有的頁數
    @Override
    public Integer getPokemonCategoryPage(PokemonQueryParams pokemonQueryParams) {
        Integer total = pokemonDao.getPokemonsCount(pokemonQueryParams);

        // 調用 共用的 頁數 計算方法
        Integer page = pageCount(pokemonQueryParams, total);

        return page;
    }

    @Override
    public List<TempPokemon> getPokemonByUserId(Integer userId) {

        return pokemonDao.getPokemonByUserId(userId);
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
