package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.BoxDao;
import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempBox;
import com.daruo.firstweb.service.BoxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoxServiceImpl implements BoxService {

    private final static Logger log = LoggerFactory.getLogger(BagServiceImpl.class);

    @Autowired
    private BoxDao boxDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private BagDao bagDao;



    @Override
    public List<TempBox> getBox(Integer userId) {

        return boxDao.getBox(userId);
    }

    @Override
    public void deleteById(Integer userId, Integer boxId) {

        try {

            // 取得 盒子內的指定商品
            TempBox tempBox = boxDao.getBoxById(userId, boxId);

            // 刪除 使用者的這項 商品
            pokemonDao.deleteById(tempBox.getUserId(), tempBox.getMyPkId());

            // 刪除 該項商品的 技能
            skillDao.deleteById(tempBox.getUserId(), tempBox.getMyPkId());

            // 刪除 盒子內的 商品
            boxDao.deleteBoxId(tempBox.getUserId(), tempBox.getMyPkId());

            // 取得盒子內 BoxId 排在 刪除商品 後面的所有商品
            List<TempBox> tempBoxList = boxDao.getBxs(userId, boxId);

            for (TempBox tpBox : tempBoxList) {

                // 建立新的變數，用來更新盒子的 ID
                Integer newBoxId = tpBox.getBoxId();

                // 更新 填補 被刪除的商品所留下來的 BoxId 空缺
                boxDao.updateBoxId(userId, tpBox.getBoxId(), newBoxId);
            }

        } catch (Exception e) {

            log.warn(e.toString());
        }
    }

    // 商品 轉交給 盒子
    @Override
    public void goToBagById(Integer userId, Integer boxId) {

        try {

            // 取得 盒子內 指定 盒子 ID 的 商品資訊
            TempBox tempBox = boxDao.getBoxById(userId, boxId);

            // 取得 背包內 最後一個 背包 ID
            Integer tempBagLastId = bagDao.getLastBagIdByUserId(userId);

            // 將購物車的商品 轉交給 盒子
            bagDao.createBagByTempBox(tempBox, tempBagLastId);

            // 刪除 購物車的該項 商品
            boxDao.deleteBoxId(tempBox.getUserId(), tempBox.getMyPkId());

            // 取得購物車內 BagId 排在 刪除商品 後面的所有商品
            List<TempBox> tempBoxList = boxDao.getBxs(userId, boxId);

            for (TempBox tpBox : tempBoxList) {

                // 建立新的變數，用來更新背包的 ID
                Integer newBoxId = tpBox.getBoxId();

                // 更新 填補 被刪除的商品所留下來的 BagId 空缺
                boxDao.updateBoxId(userId, tpBox.getBoxId(), newBoxId);
            }

        } catch (Exception e) {

            log.warn(e.toString());
        }

    }
}
