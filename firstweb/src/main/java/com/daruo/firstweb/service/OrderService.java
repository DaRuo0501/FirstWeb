package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempOrder;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.Order;

import java.util.List;

public interface OrderService {

    void createOrderById(TempOrder tempOrder, TempUser tempUser);

    List<TempOrder> getOrderById(Integer userId);
}
