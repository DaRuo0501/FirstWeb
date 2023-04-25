package com.daruo.firstweb.controller;

import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import com.daruo.firstweb.service.ShopCarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopCarController {

    private final static Logger log = LoggerFactory.getLogger(ShopCarController.class);

    @Autowired
    private ShopCarService shopCarService;

    @Autowired
    private PokemonService pokemonService;

    // 商品 預購買數量 +1
    @GetMapping("/shopCar/addCount/{pokemonId}")
    public String addCount(@PathVariable(name = "pokemonId") Integer pokemonId,
                           HttpServletRequest request,
                           HttpSession session
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");

            ShopCar shopCar = new ShopCar();
            shopCar.setUserId(user.getUserId());
            shopCar.setPokemonId(pokemonId);

            // 取得 預購買數量
            ShopCar tempBuyCnt = shopCarService.getBuyCnt(shopCar);

            // 取得 商品
            Pokemon pokemon = pokemonService.getPokemonById(pokemonId);

            // 檢查 商品 預購買數量 是否 大於 & 等於 商品的 庫存數量
            if (pokemon.getStock() <= tempBuyCnt.getBuyCnt()) {

                log.warn("此商品: " + pokemon.getPokemonName() + "，沒有更多庫存了!!!");

                return "redirect:/users/shopCar";
            }

            // 預購買數量 +1
            shopCarService.addCount(tempBuyCnt);


        } catch (Exception e) {
            log.warn(e.toString());
        }

        return "redirect:/users/shopCar";
    }

    // 商品 預購買數量 -1
    @GetMapping("/shopCar/reduceCount/{pokemonId}")
    public String reduceCount(@PathVariable(name = "pokemonId") Integer pokemonId,
                           HttpServletRequest request,
                           HttpSession session
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");

            ShopCar shopCar = new ShopCar();
            shopCar.setUserId(user.getUserId());
            shopCar.setPokemonId(pokemonId);

            // 取得 預購買數量
            ShopCar tempBuyCnt = shopCarService.getBuyCnt(shopCar);

            // 取得 商品
            Pokemon pokemon = pokemonService.getPokemonById(pokemonId);

            // 檢查 商品 預購買數量 是否 小於 & 等於 0
            if (tempBuyCnt.getBuyCnt() <= 0) {

                log.warn("此商品: " + pokemon.getPokemonName() + "，不能買 -1 個!!!");

                return "redirect:/users/shopCar";
            }

            // 預購買數量 -1
            shopCarService.reduceCount(tempBuyCnt);


        } catch (Exception e) {
            log.warn(e.toString());
        }

        return "redirect:/users/shopCar";
    }
}
