package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.*;
import com.daruo.firstweb.dto.*;
import com.daruo.firstweb.service.OrderService;
import jakarta.servlet.http.HttpSession;
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
    public void createOrderById(TempOrder tempOrder, TempUser tempUser, HttpSession session) {

        try {

            // 檢查 前端 是否有傳入 商品
            if (tempOrder.getTotalAmount() > 0) {

                // 取得 使用者的購物車內容
                List<TempShopCar> tempShopCarList = shopCarDao.getShopCarList(tempOrder.getUserId());


                for (TempShopCar tempShopCar : tempShopCarList) {

                    // 查詢 欲購買的商品 架上是否有貨
                    TempPokemon tempPokemon = pokemonDao.getPokemonById(tempShopCar.getPokemonId());

                    if (tempPokemon.getStock() > 0) {

                        tempPokemon.setStock(tempPokemon.getStock() - tempShopCar.getBuyCnt());

                        // 扣除貨架上的商品數量
                        pokemonDao.updatePokemonCountById(tempShopCar.getPokemonId(), tempPokemon.getStock());

                        // 將商品 放入使用者的背包
                        bagDao.createBag(tempOrder.getUserId(), tempPokemon);

                    } else {

                        log.warn("商品數量不足!只剩下 {} 個", tempPokemon.getStock());

                        String errorStr = "此商品: " + tempPokemon.getPokemonName() +
                                " ，庫存不足! 只剩下" + tempPokemon.getStock() + "個!";

                        ErrorMsg(errorStr, session);
                    }
                }

                // 建立訂單
                orderDao.createOrderById(tempOrder);

                // 移除購物車內已成功建立訂單的商品
                shopCarDao.deleteShopCarByUserId(tempOrder.getUserId());

                // 待更新的現金 = 使用者擁有的現金 - 訂單的總金額
                tempUser.setMoney(tempUser.getMoney() - tempOrder.getTotalAmount());

                // 更新 使用者的 現金
                userDao.updateUserMoney(tempOrder.getUserId(), tempUser.getMoney());

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

    public static void ErrorMsg(String errorStr, HttpSession session) {

        Msg msg = new Msg();
        msg.setText(errorStr);

        session.setAttribute("msg", msg);
        session.setAttribute("errorFlag", "Y");

    }
}
