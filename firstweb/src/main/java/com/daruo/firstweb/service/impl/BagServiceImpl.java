package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.BoxDao;
import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempPokemon;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.service.BagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BagServiceImpl implements BagService {

    private final static Logger log = LoggerFactory.getLogger(BagServiceImpl.class);

    @Autowired
    private BagDao bagDao;

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private BoxDao boxDao;

    @Override
    public List<TempBag> getBag(Integer userId) {

        return bagDao.getBag(userId);
    }

    // 刪除
    @Transactional
    @Override
    public void deleteById(Integer userId, Integer bagId) {

        try {

            // 刪除 商品
            bagDao.deleteBagId(userId, bagId);

            // 取得購物車內 BagId 排在 刪除商品 後面的所有商品
            List<TempBag> tempBagList = bagDao.getBags(userId, bagId);

            for (TempBag tempBag : tempBagList) {

                // 建立新的變數，用來更新背包的 ID
                Integer newBagId = tempBag.getBagId();

                // 更新 填補 被刪除的商品所留下來的 BadId 空缺
                bagDao.updateBagId(userId, tempBag.getBagId(), newBagId);
            }

        } catch (Exception e) {

            log.warn(e.toString());
        }
    }

    // 商品 轉交給 盒子
    @Override
    public void goToBoxById(Integer userId, Integer bagId) {

        try {

            // 取得 購物車內 指定 背包 ID 的 商品資訊
            TempBag tempBag = bagDao.getBagById(userId, bagId);

            // 取得 盒子內 最後一個 盒子 ID
            Integer tempBoxLastId = boxDao.getLastBoxIdByUserId(userId);

            // 將購物車的商品 轉交給 盒子
            boxDao.createBoxByTempBag(tempBag, tempBoxLastId);

            // 刪除 購物車的該項 商品
            bagDao.deleteBagId(userId, bagId);

            // 取得購物車內 BagId 排在 刪除商品 後面的所有商品
            List<TempBag> tempBagList = bagDao.getBags(userId, bagId);

            for (TempBag tpBag : tempBagList) {

                // 建立新的變數，用來更新背包的 ID
                Integer newBagId = tpBag.getBagId();

                // 更新 填補 被刪除的商品所留下來的 BagId 空缺
                bagDao.updateBagId(userId, tpBag.getBagId(), newBagId);
            }

        } catch (Exception e) {

            log.warn(e.toString());
        }

    }
}
