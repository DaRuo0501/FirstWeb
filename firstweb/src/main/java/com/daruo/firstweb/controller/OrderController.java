package com.daruo.firstweb.controller;

import com.daruo.firstweb.model.Order;
import com.daruo.firstweb.model.ShopCar;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.OrderService;
import com.daruo.firstweb.service.ShopCarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopCarService shopCarService;

    // 建立訂單
    @GetMapping("/orders/create/{totalAmount}")
    public String createOrder(@PathVariable Integer totalAmount,
                              HttpServletRequest request,
                              HttpSession session) {

        // 取得 當前使用者
        session = request.getSession();
        User user = (User) session.getAttribute("showUserName");

        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setTotalAmount(totalAmount);

        // 建立訂單
        orderService.createOrderById(order);

        // 扣除商品架上的數量
        shopCarService.removePokemonCount(user);

        // 清除已轉入訂單中，購物車內的商品
        shopCarService.removeShopCarByUserId(user);

        return "redirect:/users/shopCar";
    }
}
