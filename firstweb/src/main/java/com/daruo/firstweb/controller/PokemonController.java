package com.daruo.firstweb.controller;

import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    // 查詢
    @GetMapping("/pokemons/get")
    public String getPokemons(@ModelAttribute PokemonQueryParams pokemonQueryParams,
                              Model model,

                              // 查詢條件 Filtering
                              @RequestParam(required = false) PokemonCategory category,

                              // 排序 Sorting
                              @RequestParam(defaultValue = "pokemon_id") String orderBy,
                              @RequestParam(defaultValue = "asc") String sort,

                              // 分頁 Pagination
                              @RequestParam(defaultValue = "12") @Max(1000) @Min(0) Integer limit,
                              @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        pokemonQueryParams.setPokemonCategory(category);
        pokemonQueryParams.setOrderBy(orderBy);
        pokemonQueryParams.setSort(sort);
        pokemonQueryParams.setLimit(limit);
        pokemonQueryParams.setOffset(offset);

        // 關鍵字 查詢
        List<Pokemon> pokemonList = pokemonService.getPokemons(pokemonQueryParams);

        model.addAttribute("pokemons", pokemonList);

        // 屬性 查詢
        List<Pokemon> categorys = pokemonService.getCategory();

        model.addAttribute("categorys", categorys);

        return "shop";
    }

}
