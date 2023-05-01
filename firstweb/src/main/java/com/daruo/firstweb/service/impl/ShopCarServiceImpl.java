package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dao.UserDao;
import com.daruo.firstweb.dto.Msg;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.service.ShopCarService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopCarServiceImpl implements ShopCarService {

    private final static Logger log = LoggerFactory.getLogger(ShopCarServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private ShopCarDao shopCarDao;

    @Override
    public List<TempShopCar> getShopCarList(Integer userId) {

        return shopCarDao.getShopCarList(userId);
    }

    @Override
    public TempShopCar getBuyCnt(TempShopCar tempShopCar) {
        return shopCarDao.getBuyCnt(tempShopCar);
    }

    @Override
    public void addCount(TempShopCar tempShopCar) {

        shopCarDao.addCount(tempShopCar);
    }

    @Override
    public void reduceCount(TempShopCar tempShopCar) {

        shopCarDao.reduceCount(tempShopCar);
    }

    @Override
    public void deletePokemonById(Integer userId, Integer pokemoId) {

        shopCarDao.deletePokemonById(userId, pokemoId);
    }

    @Override
    public void updateAmountById(TempShopCar tempShopCar, TempPokemon tempPokemon) {

        shopCarDao.updateAmountById(tempShopCar, tempPokemon);
    }

    @Override
    public void updateBuyCntById(TempShopCar tempShopCar) {

        shopCarDao.updateBuyCntById(tempShopCar);
    }

    // 建立 購物車
    @Override
    public void createShopCar(TempUser tempUser, Integer pokemonId, HttpSession session) {

        try {

            // 查詢 購物車內 與前端傳遞進來 相同 Id 的商品
            TempShopCar tpShopCar = shopCarDao.getShopCarPokemonByPokemonId(tempUser.getUserId(), pokemonId);

            if (tpShopCar == null) {

                // 取得 使用者的完整資訊
                TempUser tpUser = userDao.getTempUserById(tempUser.getUserId());

                // 取得 商品的完整資訊
                TempPokemon tempPokemon = pokemonDao.getTempPokemonById(pokemonId);

                // 取得 使用者的購物車資訊
                List<TempShopCar> tempShopCarList = shopCarDao.getShopCarList(tpUser.getUserId());

                // 取得 使用者 購物車內的 最後一筆資訊
                TempShopCar tempShopCar = shopCarDao.getShopCarLastPokemonByUserId(tpUser.getUserId());


                // 確認 購物車 內是否已經有 商品
                if (tempShopCarList.size() > 0) {

                    // 添加一筆 商品進購物車
                    shopCarDao.addShopCar(tempUser, tempPokemon , tempShopCar);

                } else {

                    // 建立 第一筆 購物車的 商品
                    shopCarDao.createFirstShopCar(tpUser, tempPokemon);
                }

            } else {

                log.warn("購物車內已經有相同商品: {}", tpShopCar.getPokemonName());

                String errorStr = "此商品: " + tpShopCar.getPokemonName() + " ，已在購物車內!";

                ErrorMsg(errorStr, session);
            }

        } catch (Exception e) {
            log.warn(e.toString());
        }
    }

    public static void ErrorMsg(String errorStr, HttpSession session) {

        Msg msg = new Msg();
        msg.setText(errorStr);

        session.setAttribute("msg", msg);
        session.setAttribute("errorFlag", "Y");

    }
}
