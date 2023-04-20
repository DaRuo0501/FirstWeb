package com.daruo.firstweb.controller;


import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.PokemonQueryParams;
import com.daruo.firstweb.dto.UserQueryParams;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.PokemonService;
import com.daruo.firstweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PokemonService pokemonService;

    private Pokemon pokemon;

    // 登入頁面
    @GetMapping(value = {"/users/login", "/"})
    public String login() {
        return "login";
    }

    // 註冊頁面
    @GetMapping("/users/register")
    public String register() {
        return "register";
    }

    // 首頁
    @GetMapping("/users/home")
    public String home(Model model,

                       // 分頁 Pagination
                       @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                       @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        List<User> userList = userService.getAllUsers(userQueryParams);

        model.addAttribute("users", userList);

        return "home";
    }

    // 修改使用者頁面
    @GetMapping("/users/goToUpdatePage/{userId}")
    public String goToUpdateUserPage(@PathVariable(name = "userId") Integer userId,
                                     Model model) {

        User user = userService.getUserById(userId);

        model.addAttribute("updateUser", user);

        if (user == null) {
            throw new RuntimeException();
        } else {

            return "userUpdate";
        }
    }

    // 個人資料頁面
    @GetMapping("/users/user")
    public String userPage() {
        return "user";
    }

    // 背包
    @GetMapping("/users/bag")
    public String bag() {
        return "bag";
    }

    // 商城頁面
    @GetMapping("users/shop")
    public String shop(Model model,
                       HttpSession session,

                       // 查詢條件 Filtering
                       @RequestParam(required = false) PokemonCategory category,
                       @RequestParam(required = false) String search,

                       // 排序 Sorting
                       @RequestParam(defaultValue = "pokemon_id") String orderBy,
                       @RequestParam(defaultValue = "asc") String sort,

                       // 分頁 Pagination
                       @RequestParam(defaultValue = "12") @Max(1000) @Min(0) Integer limit,
                       @RequestParam(defaultValue = "0") @Min(0) Integer offset,
                       @RequestParam(defaultValue = "1") @Min(1) Integer page
    ) {



        // 將前端傳入的 Page, Category 存入 Session，使前端可以調用，並使其成為選取後的顯示的欄位參數
        session.setAttribute("nowPage", page);
        session.setAttribute("nowCategory", category);

        // 查詢的條件中帶有 屬性 ，頁數預設呈現第一頁
        if (category != null) {
            page = 1;
        }

        // 每一頁的第一筆 = 頁數 * 單頁數量 - 單頁數量
        offset = page * limit - limit;

        PokemonQueryParams pokemonQueryParams = new PokemonQueryParams();
        pokemonQueryParams.setPokemonCategory(category);
        pokemonQueryParams.setSearch(search);
        pokemonQueryParams.setOrderBy(orderBy);
        pokemonQueryParams.setSort(sort);
        pokemonQueryParams.setLimit(limit);
        pokemonQueryParams.setOffset(offset);

        // 獲取 寶可夢
        List<Pokemon> pokemonList = pokemonService.getPokemons(pokemonQueryParams);

        model.addAttribute("pokemons", pokemonList);

        // 獲取 屬性
        List<Pokemon> categorys = pokemonService.getCategory();

        model.addAttribute("categorys", categorys);

        // 獲取 頁數
        List<Integer> pages = pokemonService.getPokemonsCount(pokemonQueryParams);

        model.addAttribute("pages", pages);

        return "shop";
    }

    @GetMapping("/users/shopCar")
    public String shopCar(Model model) {
        return "shopCar";
    }

    // 登出(返回登入頁面)
    @GetMapping("/users/logout")
    public String logout() {
        return "redirect:/users/login";
    }

}
