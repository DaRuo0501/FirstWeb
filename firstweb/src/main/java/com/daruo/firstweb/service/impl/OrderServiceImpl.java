package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.OrderDao;
import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.ShopCarDao;
import com.daruo.firstweb.dto.TempOrder;
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
    private BagDao bagDao;

    @Transactional
    @Override
    public void createOrderById(Order order) {

        try {

            // 檢查 前段 是否有傳入 商品
            if (order.getTotalAmount() > 0) {

                // 有傳入 商品 可以 建立訂單
                orderDao.createOrderById(order);

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
