package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.Msg;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
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
public class PokemonController {

    private final static Logger log = LoggerFactory.getLogger(PokemonController.class);

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private UserService userService;

    // 新增商品至購物車
    @GetMapping("/pokemons/addShopCar/{pokemonId}")
    public String createShopCar(@PathVariable(name = "pokemonId") Integer pokemonId,
                                Model model,
                                HttpSession session,
                                HttpServletRequest request) {

        try {

            // 取得 當前登入的使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");
            User tempUser = userService.getUserByName(user);
            user.setUserId(tempUser.getUserId());

            // 取得 商品 資訊
            Pokemon pokemon = pokemonService.getPokemonById(pokemonId);

            // 檢查 庫存數量
            if (pokemon.getStock() > 0) {

                // 將 商品 放進 購物車
                Pokemon tempPokemon = pokemonService.createShopCarById(pokemon, user);

                if (tempPokemon == null) {

                    log.warn("此商品: {}，已在購物車內!", pokemon.getPokemonName());

                    String errorStr = "此商品: " + pokemon.getPokemonName() + " ，已在購物車內!";

                    ErrorMsg(errorStr, session);
                }

                return "redirect:/users/shop";

            } else { // 庫存為 0 ，返回庫存不足的訊息給前端使用者

                log.warn("此商品: {} 的庫存為 : 0 個，無法購買!", pokemon.getPokemonName());

                String errorStr = "此商品: " + pokemon.getPokemonName() + " 的庫存為 : 0 個，無法購買!";

                ErrorMsg(errorStr, session);

            }

        } catch (Exception e) {

            log.warn(e.getMessage());
        }

        return "redirect:/users/shop";
    }

    public static void ErrorMsg(String errorStr, HttpSession session) {

        Msg msg = new Msg();
        msg.setText(errorStr);

        session.setAttribute("msg", msg);
        session.setAttribute("errorFlag", "Y");

    }
}
