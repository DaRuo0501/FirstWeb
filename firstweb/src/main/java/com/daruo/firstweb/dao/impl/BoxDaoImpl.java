package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.BoxDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempBox;
import com.daruo.firstweb.rowmapper.LastBagRowMapper;
import com.daruo.firstweb.rowmapper.LastBoxRowMapper;
import com.daruo.firstweb.rowmapper.TempBagRowMapper;
import com.daruo.firstweb.rowmapper.TempBoxRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoxDaoImpl implements BoxDao {

    private final static Logger log = LoggerFactory.getLogger(BoxDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // 新增 商品 至 背包
    @Override
    public void createBox(Integer userId, Integer myPkId, Integer boxId) {

        try {

            String sql = "INSERT INTO box (box_id, user_id, my_pk_id, created_date, last_modified_date) " +
                    "VALUES (:boxId, :userId, :myPkId, :createdDate, :lastModifiedDate);";

            Map<String, Object> map = new HashMap<>();
            map.put("boxId", boxId + 1);
            map.put("userId", userId);
            map.put("myPkId", myPkId);

            Date now = new Date();
            map.put("createdDate", now);
            map.put("lastModifiedDate", now);

            namedParameterJdbcTemplate.update(sql, map);

        } catch (Exception e) {

            log.error(e.toString());
        }
    }

    @Override
    public Integer getLastBoxIdByUserId(Integer userId) {

        String sql = "SELECT box_id, user_id, my_pk_id, created_date, last_modified_date " +
                "FROM box WHERE user_id = :userId ORDER BY box_id DESC;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBox> tempBoxList = namedParameterJdbcTemplate.query(sql, map , new LastBoxRowMapper());

        if (tempBoxList.size() > 0) {

            return tempBoxList.get(0).getBoxId();

        } else {

            return 0;
        }
    }

    @Override
    public void createBoxByTempBag(TempBag tempBag, Integer boxId) {

        try {

            String sql = "INSERT INTO box (box_id, user_id, my_pk_id, created_date, last_modified_date) " +
                    "VALUES (:boxId, :userId, :myPkId, :createdDate, :lastModifiedDate);";

            Map<String, Object> map = new HashMap<>();
            map.put("boxId", boxId + 1);
            map.put("userId", tempBag.getUserId());
            map.put("myPkId", tempBag.getMyPkId());

            Date now = new Date();
            map.put("createdDate", now);
            map.put("lastModifiedDate", now);

            namedParameterJdbcTemplate.update(sql, map);

        } catch (Exception e) {

            log.error(e.toString());
        }
    }

    @Override
    public List<TempBox> getBox(Integer userId) {

        String sql = "SELECT * FROM box join my_pokemon_value mpv on box.my_pk_id = mpv.my_pk_id" +
                " WHERE box.user_id = :userId ORDER BY box_id ASC;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<TempBox> tempBoxList = namedParameterJdbcTemplate.query(sql, map, new TempBoxRowMapper());

        return tempBoxList;
    }

    @Override
    public TempBox getBoxById(Integer userId, Integer boxId) {

        String sql = "SELECT box.box_id, box.user_id, box.my_pk_id, mpv.pokemon_id, mpv.pokemon_name," +
                " mpv.category, mpv.hp, mpv.lv, mpv.exp, mpv.pokemon_image_url," +
                " mpv.attack, mpv.defense, mpv.speed, mpv.description," +
                " mpv.created_date, mpv.last_modified_date" +
                " FROM box" +
                " join my_pokemon_value mpv ON box.my_pk_id = mpv.my_pk_id" +
                " WHERE box.user_id = :userId AND box.box_id = :boxId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("boxId", boxId);

        List<TempBox> tempBoxList = namedParameterJdbcTemplate.query(sql, map , new TempBoxRowMapper());

        if (tempBoxList.size() > 0) {

            return tempBoxList.get(0);

        }

        return null;
    }

    @Override
    public void deleteBoxId(Integer userId, Integer myPkId) {

        String sql = "DELETE FROM box WHERE user_id = :userId AND my_pk_id = :myPkId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("myPkId", myPkId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<TempBox> getBxs(Integer userId, Integer boxId) {

        String sql = "SELECT box_id, user_id, my_pk_id, created_date, last_modified_date " +
                " FROM box WHERE user_id = :userId AND box_id > :boxId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("boxId", boxId);

        List<TempBox> tempBoxList = namedParameterJdbcTemplate.query(sql, map , new LastBoxRowMapper());

        return tempBoxList;
    }

    @Override
    public void updateBoxId(Integer userId, Integer boxId, Integer newBoxId) {

        String sql = "UPDATE box SET box_id = :newBoxId WHERE user_id = :userId AND box_id = :boxId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("boxId", boxId);
        map.put("newBoxId", newBoxId - 1);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
