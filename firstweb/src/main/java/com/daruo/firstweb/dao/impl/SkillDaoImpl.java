package com.daruo.firstweb.dao.impl;

import com.daruo.firstweb.dao.SkillDao;
import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempSkill;
import com.daruo.firstweb.rowmapper.TempSkillCountRowMapper;
import com.daruo.firstweb.rowmapper.TempSkillListNewRowMapper;
import com.daruo.firstweb.rowmapper.TempSkillListRowMapper;
import com.daruo.firstweb.rowmapper.TempSkillRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SkillDaoImpl implements SkillDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TempSkill> getSkillByPokemonId(Integer pokemonId) {

        String sql = "SELECT * FROM pokemon JOIN pokemon_skill ps on pokemon.pokemon_id = ps.pokemon_id" +
                " JOIN skill s on ps.skill_id = s.skill_id " +
                " WHERE ps.pokemon_id = :pokemonId;";

        Map<String, Object> map = new HashMap<>();
        map.put("pokemonId", pokemonId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillListRowMapper());

        return tempSkillList;
    }

    @Override
    public void remove(Integer userId, Integer myPkId, Integer skillId) {

        String sql = "DELETE FROM my_pokemon_skill mps WHERE mps.user_id = :userId AND mps.my_pk_id = :myPkId AND mps.skill_id = :skillId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("myPkId", myPkId);
        map.put("skillId", skillId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void createUserPokemonSkill(Integer myPkId, Integer userId, TempSkill tempSkill) {

        String sql = "INSERT INTO my_pokemon_skill(my_pk_id, user_id, pokemon_id, pokemon_name, skill_id, skill_name," +
                " created_date, last_modified_date)" +
                " VALUES (:myPkId, :userId, :pokemonId, :pokemonName, :skillId, :skillName, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("myPkId", myPkId);
        map.put("userId", userId);
        map.put("pokemonId", tempSkill.getPokemonId());
        map.put("pokemonName", tempSkill.getPokemonName());
        map.put("skillId", tempSkill.getSkillId());
        map.put("skillName", tempSkill.getSkillName());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(Integer userId, Integer myPkId) {

        String sql = "DELETE FROM my_pokemon_skill WHERE my_pk_id = :myPkId AND user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("myPkId", myPkId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<TempSkill> getSkillByMyPkId(Integer myPkId) {

        String sql = "SELECT * FROM bag b JOIN my_pokemon_skill mps on b.my_pk_id = mps.my_pk_id" +
                " JOIN skill s on mps.skill_id = s.skill_id WHERE b.my_pk_id = :myPkId;";

        Map<String, Object> map = new HashMap<>();
        map.put("myPkId", myPkId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillListRowMapper());

        return tempSkillList;
    }

    @Override
    public List<TempSkill> getPokemonNewSkill(TempBag tempBag) {

        String sql = "SELECT * FROM skill_item si join skill s on si.skill_id = s.skill_id" +
                " WHERE si.category = :category AND si.lv <= :lv;";

        Map<String, Object> map = new HashMap<>();
        map.put("category", tempBag.getCategory().toString());
        map.put("lv", tempBag.getLv());

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillListNewRowMapper());

        return tempSkillList;
    }

    @Override
    public TempSkill getSkillName(Integer skillId) {

        String sql = "SELECT * FROM skill WHERE skill_id = :skillId;";

        Map<String, Object> map = new HashMap<>();
        map.put("skillId", skillId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillRowMapper());

        if (tempSkillList.size() > 0) {

            return tempSkillList.get(0);
        }

        return null;
    }

    @Override
    public Integer getCountSkill(Integer myPkId) {

        String sql = "SELECT * FROM my_pokemon_skill WHERE my_pk_id = :myPkId;";

        Map<String, Object> map = new HashMap<>();
        map.put("myPkId", myPkId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillCountRowMapper());

        Integer total = tempSkillList.size();

        return total;
    }

    @Override
    public TempSkill getSkillNameByMyPkId(Integer myPkId, Integer skillId) {

        String sql = "SELECT * FROM my_pokemon_skill WHERE my_pk_id = :myPkId AND skill_id = :skillId;";

        Map<String, Object> map = new HashMap<>();
        map.put("myPkId", myPkId);
        map.put("skillId", skillId);

        List<TempSkill> tempSkillList = namedParameterJdbcTemplate.query(sql, map, new TempSkillCountRowMapper());

        if (tempSkillList.size() > 0) {

            return tempSkillList.get(0);
        }

        return null;
    }

    @Override
    public void add(TempBag tempBag, TempSkill tempSkill) {

        String sql = "INSERT INTO my_pokemon_skill(my_pk_id, user_id, pokemon_id, pokemon_name, skill_id, skill_name," +
                " created_date, last_modified_date)" +
                " VALUES (:myPkId, :userId, :pokemonId, :pokemonName, :skillId, :skillName, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("myPkId", tempBag.getMyPkId());
        map.put("userId", tempBag.getUserId());
        map.put("pokemonId", tempBag.getPokemonId());
        map.put("pokemonName", tempBag.getPokemonName());
        map.put("skillId", tempSkill.getSkillId());
        map.put("skillName", tempSkill.getSkillName());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, map);

    }
}
