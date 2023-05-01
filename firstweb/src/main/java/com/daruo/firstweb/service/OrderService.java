package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempOrder;
import com.daruo.firstweb.dto.TempUser;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface OrderService {

    void createOrderById(TempOrder tempOrder, TempUser tempUser, HttpSession session);

    List<TempOrder> getOrderById(Integer userId);
}
