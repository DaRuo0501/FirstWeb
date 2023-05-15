package com.daruo.firstweb.controller;


import com.daruo.firstweb.constant.PokemonCategory;
import com.daruo.firstweb.dto.*;
import com.daruo.firstweb.model.Pokemon;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.*;
import com.daruo.firstweb.util.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {

    private final static Logger log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private ShopCarService shopCarService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BagService bagService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private BoxService boxService;

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
    public String home() {
        return "home";
    }

    // 個人資料頁面
    @GetMapping("/users/user")
    public String userPage(Model model,
                           HttpSession session,
                           HttpServletRequest request
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            TempUser tempUser = (TempUser) session.getAttribute("showUserName");

            TempUser tpUser = userService.getUserById(tempUser.getUserId());

            model.addAttribute("user", tpUser);

            List<TempPokemon> tempPokemonList = pokemonService.getPokemonByUserId(tempUser.getUserId());

            model.addAttribute("pokemons", tempPokemonList);

        } catch (Exception e) {
            log.error(e.toString());
        }

        return "user";
    }

    // 背包
    @GetMapping("/users/bag")
    public String bag(Model model,
                      HttpSession session,
                      HttpServletRequest request,

                      // 查詢條件 Filtering
                      @RequestParam(defaultValue = "0") @Min(0) @Max(5) Integer bagId
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            TempUser tempUser = (TempUser) session.getAttribute("showUserName");

            // 取得 使用者的背包
            List<TempBag> tempBagList = bagService.getBag(tempUser.getUserId());

            model.addAttribute("bagList", tempBagList);

            model.addAttribute("bagId", bagId);

            // 取得 背包的 第一個商品
            String tempPokemonName = tempBagList.get(bagId).getPokemonName();

            model.addAttribute("pokemonName", tempPokemonName);

            return "bag";

        } catch (Exception e) {

            log.warn(e.toString());
        }

        return "bag";
    }

    // 修改技能頁面
    @GetMapping("/users/skill/{bagId}")
    public String skill(@PathVariable Integer bagId,
                        Model model,
                        HttpServletRequest request,
                        HttpSession session
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            TempUser tempUser = (TempUser) session.getAttribute("showUserName");

            TempBag tempBag = bagService.goToSkillUpdatePage(tempUser.getUserId(), bagId);

            model.addAttribute("bagPokemon", tempBag);

            // 取得 商品的 技能
            List<TempSkill> tempSkillList = skillService.getSkillByMyPkId(tempBag.getMyPkId(), tempUser.getUserId());

            model.addAttribute("skillList", tempSkillList);

            List<TempSkill> tempSkillListNew = skillService.getPokemonNewSkill(tempBag);

            model.addAttribute("newSkillList", tempSkillListNew);

            return "skill";

        } catch (Exception e) {

            log.warn(e.getMessage());
        }

        return "skill";
    }

    // 盒子 (倉庫)
    @GetMapping("/users/box")
    public String box(Model model,
                      HttpSession session,
                      HttpServletRequest request
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            TempUser tempUser = (TempUser) session.getAttribute("showUserName");

            List<TempBox> tempBoxList = boxService.getBox(tempUser.getUserId());

            model.addAttribute("boxList", tempBoxList);


        } catch (Exception e) {
            log.error(e.toString());
        }

        return "box";
    }

    // 商城頁面
    @GetMapping("/users/shop")
    public String shop(Model model,
                       Msg msg,
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
                       @RequestParam(defaultValue = "1") @Min(1) Integer page,
                       @RequestParam(defaultValue = "0") @Min(0) Integer priceMin,
                       @RequestParam(defaultValue = "100000000") @Max(100000000) @Min(0) Integer priceMax
    ) {

        try {

            // 將前端傳入的 Page, Category 存入 Session，使前端可以調用，並使其成為選取後的顯示的欄位參數
            session.setAttribute("nowPage", page);
            session.setAttribute("nowCategory", category);

            PokemonQueryParams pokemonQueryParams = new PokemonQueryParams();
            pokemonQueryParams.setPokemonCategory(category);
            pokemonQueryParams.setSearch(search);
            pokemonQueryParams.setOrderBy(orderBy);
            pokemonQueryParams.setSort(sort);
            pokemonQueryParams.setLimit(limit);
            pokemonQueryParams.setPriceMin(priceMin);
            pokemonQueryParams.setPriceMax(priceMax);

        /*
        預設進入商城，查詢全部商品
        屬性為 null
         */
            if (category != null) {

                // 查詢的條件中帶有 屬性，先查詢該屬性的總頁數
                Integer tempPage = pokemonService.getPokemonCategoryPage(pokemonQueryParams);

                // 屬性的總頁數 小於 前端傳入頁數，頁數預設結果為該屬性的第一頁
                if (tempPage < page) {
                    page = 1;
                }
            }

            // 每一頁的第一筆 = 頁數 * 單頁數量 - 單頁數量
            offset = page * limit - limit;

            pokemonQueryParams.setOffset(offset);

            // 獲取 寶可夢
            List<TempPokemon> tempPokemonList = pokemonService.getPokemons(pokemonQueryParams);

            model.addAttribute("pokemons", tempPokemonList);

            // 獲取 屬性
            List<TempPokemon> categorys = pokemonService.getCategory();

            model.addAttribute("categorys", categorys);

            // 獲取 頁數
            List<Integer> pages = pokemonService.getPokemonsPage(pokemonQueryParams);

            model.addAttribute("pages", pages);

            // session 不是 null
            if (session.getAttribute("msg") != null) {

                // 取出 session 中的內容
                Msg tempMsg = (Msg) session.getAttribute("msg");

                // 檢查錯誤訊息，是否執行過
                if (tempMsg.getCount() == 1) {

                    // msg 的默認值覆蓋重寫
                    session.setAttribute("msg", msg);

                    // 錯誤訊息已經執行過 count -1
                    tempMsg.setCount(0);

                } else {

                    // 錯誤訊息執行 count +1
                    tempMsg.setCount(1);

                    // session 的內容是空白
                    if ("".equals(tempMsg.getText()))

                        // msg 的默認值覆蓋重寫
                        session.setAttribute("msg", msg);

                }

            } else {

                // msg 的默認值覆蓋重寫
                session.setAttribute("msg", msg);
            }

        } catch (Exception e) {

            log.error(e.toString());
        }
        return "shop";
    }

    // 購物車
    @GetMapping("/users/shopCar")
    public String shopCar(Model model,
                          Msg msg,
                          HttpSession session,
                          HttpServletRequest request
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();

            TempUser tempUser = (TempUser) session.getAttribute("showUserName");

            // 取得 使用者的 購物車清單
            List<TempShopCar> tempShopCarList = shopCarService.getShopCarList(tempUser.getUserId());

            int totalAmount = 0;

            // 將每一筆的金額 加總起來
            for (TempShopCar tempShopCar : tempShopCarList) {

                totalAmount += tempShopCar.getAmount();
            }

            model.addAttribute("totalAmount", totalAmount);

            model.addAttribute("shopCars", tempShopCarList);

            TempUser tpUser = userService.getUserById(tempUser.getUserId());

            model.addAttribute("user", tpUser);

            // session 不是 null
            if (session.getAttribute("msg") != null) {

                // 取出 session 中的內容
                Msg tempMsg = (Msg) session.getAttribute("msg");

                // 檢查錯誤訊息，是否執行過
                if (tempMsg.getCount() == 1) {

                    // msg 的默認值覆蓋重寫
                    session.setAttribute("msg", msg);

                    // 錯誤訊息已經執行過 count -1
                    tempMsg.setCount(0);

                } else {

                    // 錯誤訊息執行 count +1
                    tempMsg.setCount(1);

                    // session 的內容是空白
                    if ("".equals(tempMsg.getText()))

                        // msg 的默認值覆蓋重寫
                        session.setAttribute("msg", msg);

                }

            } else {

                // msg 的默認值覆蓋重寫
                session.setAttribute("msg", msg);
            }

            return "shopCar";

        } catch (Exception e) {

            log.warn(e.getMessage());
        }

        return "shopCar";
    }

    // 訂單
    @GetMapping("/users/order")
    public String order(Model model,
                        HttpSession session,
                        HttpServletRequest request
    ) {

        // 取得 當前使用者
        session = request.getSession();

        TempUser tempUser = (TempUser) session.getAttribute("showUserName");

        List<TempOrder> tempOrderList = orderService.getOrderById(tempUser.getUserId());

        model.addAttribute("orders", tempOrderList);

        return "order";
    }

    // 使用者清單
    @GetMapping("/users/userList")
    public String userList(Model model,

                           // 分頁 Pagination
                           @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                           @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);


        List<TempUser> tempUserList = userService.getAllUsers(userQueryParams);

        model.addAttribute("users", tempUserList);

        return "userList";
    }

    // 修改使用者頁面
    @GetMapping("/users/goToUpdatePage/{userId}")
    public String goToUpdateUserPage(@PathVariable(name = "userId") Integer userId,
                                     Model model) {

        TempUser tempUser = userService.getUserById(userId);

        model.addAttribute("updateUser", tempUser);

        if (tempUser == null) {
            throw new RuntimeException();
        } else {

            return "userUpdate";
        }
    }

    // 登出(返回登入頁面)
    @GetMapping("/users/logout")
    public String logout() {
        return "redirect:/users/login";
    }

}
