package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.*;
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

            TempUser tempUser = new TempUser();
            tempUser.setUserId(user.getUserId());

            // 檢查 使用者的現金是否足夠
            if (user.getMoney() >= totalAmount) {

                TempOrder tempOrder = new TempOrder();
                tempOrder.setUserId(user.getUserId());  // 購買人
                tempOrder.setTotalAmount(totalAmount);  // 訂單的 總價格

                // 建立訂單
                orderService.createOrderById(tempOrder, tempUser);

            } else {

                log.warn("這張訂單的總價格為: {}，購買者: {} 的現金只剩下 {}元，訂單無法成立!",
                        totalAmount, user.getUserName(), user.getMoney());

                String errorStr = "您的現金不足! 無法提繳訂單!";

                ErrorMsg(errorStr, session);
            }

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
