package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempOrder;
import com.daruo.firstweb.model.Order;

import java.util.List;

public interface OrderDao {

    void createOrderById(TempOrder tempOrder);

    List<TempOrder> getOrderById(Integer userId);
}
