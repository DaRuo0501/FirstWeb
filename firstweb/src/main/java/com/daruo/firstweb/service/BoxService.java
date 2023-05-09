package com.daruo.firstweb.service;

import com.daruo.firstweb.dto.TempBox;

import java.util.List;

public interface BoxService {

    List<TempBox> getBox(Integer userId);

    void deleteById(Integer userId, Integer boxId);

    void goToBagById(Integer userId, Integer boxId);
}
