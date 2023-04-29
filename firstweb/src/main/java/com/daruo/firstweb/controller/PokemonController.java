package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.Msg;
import com.daruo.firstweb.dto.TempPokemon;
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

    public static void ErrorMsg(String errorStr, HttpSession session) {

        Msg msg = new Msg();
        msg.setText(errorStr);

        session.setAttribute("msg", msg);
        session.setAttribute("errorFlag", "Y");

    }
}
