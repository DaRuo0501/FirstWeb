package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.model.Order;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.BagService;
import com.daruo.firstweb.service.OrderService;
import com.daruo.firstweb.service.PokemonService;
import com.daruo.firstweb.service.ShopCarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {

    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopCarService shopCarService;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private BagService bagService;

    // 建立訂單
    @Transactional
    @GetMapping("/orders/create/{totalAmount}")
    public String createOrder(@PathVariable Integer totalAmount,
                              HttpServletRequest request,
                              HttpSession session
    ) {

        try {

            // 取得 當前使用者
            session = request.getSession();
            User user = (User) session.getAttribute("showUserName");

            // 取得 要更新的 金額
            int newMoney = user.getMoney() - totalAmount;

            // 檢查 使用者的現金是否足夠
            if (user.getMoney() >= totalAmount) {

                Order order = new Order();
                order.setUserId(user.getUserId());
                order.setTotalAmount(totalAmount);

                // 建立訂單
                orderService.createOrderById(order, newMoney);

                // 扣除商品架上的數量
                List<TempPokemon> tempPokemonList = pokemonService.removePokemonCount(user.getUserId());

                // 將 商品 放入 使用者 的背包
                for (TempPokemon tp : tempPokemonList) {

                    bagService.createBag(user.getUserId(), tp.getPokemonId(), tp.getPokemonName());
                }

                // 清除購物車內，已轉入 訂單 的 商品
                shopCarService.removeShopCarByUserId(user);

            } else {

                log.warn("這張訂單的總價格為: {}，購買者: {} 的現金只剩下 {}元，訂單無法成立!",
                        totalAmount, user.getUserName(), user.getMoney());
            }

        } catch (Exception e) {
            log.warn(e.toString());
        }

        return "redirect:/users/shopCar";
    }
}
