package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.OrderDao;
import com.daruo.firstweb.model.Order;
import com.daruo.firstweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void createOrderById(Order order) {

        orderDao.createOrderById(order);
    }
}
