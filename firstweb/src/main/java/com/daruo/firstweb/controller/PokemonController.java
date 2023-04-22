package com.daruo.firstweb.controller;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.PokemonRequest;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private UserService userService;

    // 新增商品至購物車
    @GetMapping("/pokemons/addShopCar/{pokemonId}")
    public void createShopCar(@PathVariable(name = "pokemonId") Integer pokemonId,
                             HttpServletRequest request) {

        // 取得 當前登入的使用者
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("showUserName");
        User tempUser = userService.getUserByName(user);
        user.setUserId(tempUser.getUserId());

        // 依照前端給的 pokemonId 與 當前登入的 userId ，傳入購物車
        pokemonService.createShopCarById(pokemonId, user);
    }

}
