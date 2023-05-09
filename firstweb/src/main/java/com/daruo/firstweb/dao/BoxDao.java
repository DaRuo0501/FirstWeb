package com.daruo.firstweb.dao;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempBox;

import java.util.List;

public interface BoxDao {

    void createBox(Integer userId, Integer myPkId, Integer boxId);

    Integer getLastBoxIdByUserId(Integer userId);

    void createBoxByTempBag(TempBag tempBag, Integer boxId);

    List<TempBox> getBox(Integer userId);

    TempBox getBoxById(Integer userId, Integer boxId);

    void deleteBoxId(Integer userId, Integer myPkId);

    List<TempBox> getBxs(Integer userId, Integer boxId);

    void updateBoxId(Integer userId, Integer boxId, Integer newBoxId);
}
