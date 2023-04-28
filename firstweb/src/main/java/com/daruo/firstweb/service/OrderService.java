package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempOrder;
import com.daruo.firstweb.model.Order;

import java.util.List;

public interface OrderService {

    void createOrderById(Order order, int user);

    List<TempOrder> getOrderById(Integer userId);
}
