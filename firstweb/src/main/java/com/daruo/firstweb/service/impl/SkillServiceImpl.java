package com.daruo.firstweb.service.impl;

import com.daruo.firstweb.dao.BagDao;
import com.daruo.firstweb.dao.PokemonDao;
import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.Msg;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.service.SkillService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillServiceImpl implements SkillService {

    private final static Logger log = LoggerFactory.getLogger(SkillServiceImpl.class);

    @Autowired
    private PokemonDao pokemonDao;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private BagDao bagDao;

    @Override
    public List<TempSkill> getSkillByPokemonId(Integer bagId) {

        return skillDao.getSkillByPokemonId(bagId);
    }

    // 遺忘 招式
    @Override
    public void remove(Integer userId,Integer bagId, Integer skillId) {

        TempBag tempBag = bagDao.getBagById(userId, bagId);

        skillDao.remove(tempBag.getUserId(), tempBag.getMyPkId(), skillId);
    }

    @Override
    public List<TempSkill> getSkillByMyPkId(Integer myPkId) {

        return skillDao.getSkillByMyPkId(myPkId);
    }

    @Override
    public List<TempSkill> getPokemonNewSkill(TempBag tempBag) {

        return skillDao.getPokemonNewSkill(tempBag);
    }

    // 學習 招式
    @Override
    public void add(Integer userId, Integer bagId, Integer skillId, HttpSession session) {

        try {

            // 取得 使用者背包 指定背包號碼 所放置的商品
            TempBag tempBag = bagDao.getBagById(userId, bagId);

            // 取得商品 學會的招式數量
            Integer myPkSkillCount = skillDao.getCountSkill(tempBag.getMyPkId());

            // 取得 想學習的 招式名稱
            TempSkill tempSkill = skillDao.getSkillName(skillId);

            // 確認 商品是否已學滿 4 種招式
            if (myPkSkillCount < 4) {

                // 檢查 想學習的招式 是否本身已學會
                TempSkill tpSkill = skillDao.getSkillNameByMyPkId(tempBag.getMyPkId(), skillId);

                // 招式還沒學會
                if (tpSkill == null) {

                    // 學習新招式
                    skillDao.add(tempBag, tempSkill);

                } else {

                    log.warn("此商品: {} ，已學過了 {} 這個招式!", tempBag.getPokemonName(), tempSkill.getSkillName());
                }


            } else {

                log.warn("此商品: {} ，已學滿了 4 種招式!", tempBag.getPokemonName());
            }

        } catch (Exception e) {

            log.error(e.toString());
        }
    }

    public static void ErrorMsg(String errorStr, HttpSession session) {

        Msg msg = new Msg();
        msg.setText(errorStr);

        session.setAttribute("msg", msg);
        session.setAttribute("errorFlag", "Y");

    }
}
