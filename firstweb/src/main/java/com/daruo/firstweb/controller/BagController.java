package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.BagService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BagController {

    @Autowired
    private BagService bagService;

    @GetMapping("/bag/getPokemon/{pokemonId}")
    public String getPokemonById(@PathVariable Integer pokemonId,
                                 HttpServletRequest request,
                                 HttpSession session,
                                 Model model
    ) {

        // 取得 當前使用者
        session = request.getSession();
        User user = (User) session.getAttribute("showUserName");

        TempPokemon tempPokemon = bagService.getPokemonById(user.getUserId(), pokemonId);

        model.addAttribute("skills", tempPokemon);
        return "bag";
    }
}
