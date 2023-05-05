package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempBag;

public interface BoxDao {

    void createBox(Integer userId, Integer myPkId, Integer boxId);

    Integer getLastBoxIdByUserId(Integer userId);

    void createBoxByTempBag(TempBag tempBag, Integer boxId);
}
