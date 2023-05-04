package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.Msg;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import com.daruo.firstweb.service.ShopCarService;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopCarController {

    private final static Logger log = LoggerFactory.getLogger(ShopCarController.class);

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private ShopCarService shopCarService;


    // 新增商品至購物車
    @GetMapping("/shopCar/create/{pokemonId}")
    public String createShopCar(@PathVariable(name = "pokemonId") Integer pokemonId,
                                HttpSession session,
                                HttpServletRequest request) {

        try {

            // 取得 當前登入的使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");

            TempUser tempUser = new TempUser();
            tempUser.setUserId(user.getUserId());
            tempUser.setMoney(user.getMoney());

            TempPokemon tempPokemon = pokemonService.getTempPokemonById(pokemonId);

            if (tempPokemon.getStock() > 0) {

                // 建立 購物車
                shopCarService.createShopCar(tempUser, pokemonId, session);

            } else {

                log.warn("此商品: {} 庫存為 0 個， 無法購買!", tempPokemon.getPokemonName());

                String errorStr = "此商品: " + tempPokemon.getPokemonName() + " 庫存為 0 個，無法購買!";

                ErrorMsg(errorStr, session);

            }

        } catch (Exception e) {

            log.warn(e.getMessage());
        }

        return "redirect:/users/shop";
    }

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

            TempShopCar tempShopCar = new TempShopCar();
            tempShopCar.setUserId(user.getUserId());
            tempShopCar.setPokemonId(pokemonId);

            // 取得 預購買數量
            TempShopCar tempBuyCnt = shopCarService.getBuyCnt(tempShopCar);

            // 取得 商品
            TempPokemon tempPokemon = pokemonService.getPokemonById(pokemonId);

            // 檢查 商品 預購買數量 是否 大於 & 等於 商品的 庫存數量
            if (tempPokemon.getStock() <= tempBuyCnt.getBuyCnt()) {

                log.warn("此商品: {}，沒有更多庫存了!!!", tempPokemon.getPokemonName());

                return "redirect:/users/shopCar";
            }

            // 預購買數量 +1
            shopCarService.addCount(tempBuyCnt);

            // 查詢 單一商品 取出 更新後的數量
            TempShopCar tpBuyCnt = shopCarService.getBuyCnt(tempShopCar);

            // 更新 單一商品的 購買金額
            shopCarService.updateAmountById(tpBuyCnt, tempPokemon);

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

            TempShopCar tempShopCar = new TempShopCar();
            tempShopCar.setUserId(user.getUserId());
            tempShopCar.setPokemonId(pokemonId);

            // 取得 預購買數量
            TempShopCar tempBuyCnt = shopCarService.getBuyCnt(tempShopCar);

            // 取得 商品
            TempPokemon tempPokemon = pokemonService.getPokemonById(pokemonId);

            // 檢查 商品 預購買數量 是否 小於 & 等於 0
            if (tempBuyCnt.getBuyCnt() <= 0) {

                log.warn("此商品: {}，不能買 -1 個!!!", tempPokemon.getPokemonName());

                return "redirect:/users/shopCar";
            }

            // 預購買數量 -1
            shopCarService.reduceCount(tempBuyCnt);

            // 查詢 單一商品 取出 更新後的數量
            TempShopCar tpBuyCnt = shopCarService.getBuyCnt(tempShopCar);

            // 更新 單一商品的 購買金額
            shopCarService.updateAmountById(tpBuyCnt, tempPokemon);


        } catch (Exception e) {
            log.warn(e.toString());
        }

        return "redirect:/users/shopCar";
    }

    // 更改 購買數量
    @GetMapping("/shopCar/updateCount/{pokemonId}/{buyCnt}")
    public String updateCount(@PathVariable Integer pokemonId, Integer buyCnt,
                              HttpServletRequest request,
                              HttpSession session
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");

            TempPokemon tempPokemon = pokemonService.getPokemonById(pokemonId);

            TempShopCar tempShopCar = new TempShopCar();
            tempShopCar.setUserId(user.getUserId());
            tempPokemon.setPokemonId(pokemonId);
            tempShopCar.setBuyCnt(buyCnt);

            // 更改 預購買數量
            shopCarService.updateBuyCntById(tempShopCar);

            // 更新 單一商品的 購買金額
            shopCarService.updateAmountById(tempShopCar, tempPokemon);

        } catch (Exception e) {
            log.warn(e.toString());
        }

        return "redirect:/users/shopCar";
    }


    // 商品 從購物車中移除
    @GetMapping("/shopCar/deletePokemon/{pokemonId}")
    public String deletePokemonFromShopCar(@PathVariable(name = "pokemonId") Integer pokemonId,
                                           HttpServletRequest request,
                                           HttpSession session
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");

            TempShopCar tempShopCar = new TempShopCar();
            tempShopCar.setUserId(user.getUserId());
            tempShopCar.setPokemonId(pokemonId);

            // 刪除
            shopCarService.deletePokemonById(tempShopCar.getUserId(), tempShopCar.getPokemonId());


        } catch (Exception e) {
            log.warn(e.toString());
        }

        return "redirect:/users/shopCar";
    }

    public static void ErrorMsg(String errorStr, HttpSession session) {

        Msg msg = new Msg();
        msg.setText(errorStr);

        session.setAttribute("msg", msg);
        session.setAttribute("errorFlag", "Y");

    }
}
