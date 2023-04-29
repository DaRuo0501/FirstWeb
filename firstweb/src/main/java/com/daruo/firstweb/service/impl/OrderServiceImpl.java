package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.*;
import com.daruo.firstweb.dto.TempOrder;
import com.daruo.firstweb.dto.TempShopCar;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.Order;
import com.daruo.firstweb.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private ShopCarDao shopCarDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BagDao bagDao;

    @Transactional
    @Override
    public void createOrderById(TempOrder tempOrder, TempUser tempUser) {

        try {

            // 檢查 前端 是否有傳入 商品
            if (tempOrder.getTotalAmount() > 0) {

                // 取得 使用者的購物車內容
                List<TempShopCar> tempShopCar = shopCarDao.getShopCarList(tempOrder.getUserId());

                // 扣除貨架上的商品
                // 如果有貨才可建立訂單



                // 建立訂單
                orderDao.createOrderById(tempOrder);

                // 更新 使用者的 現金
//                userDao.updateUserMoney(tempOrder.getUserId(), newMoney);

            } else {

                log.warn("此訂單，無商品!");
            }

        } catch (Exception e) {
            log.warn(e.toString());
        }
    }

    @Override
    public List<TempOrder> getOrderById(Integer userId) {

        return orderDao.getOrderById(userId);
    }
}
