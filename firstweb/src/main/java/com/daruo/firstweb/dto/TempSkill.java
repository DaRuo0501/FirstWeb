package com.daruo.firstweb.dto;

import com.daruo.firstweb.constant.SkillCategory;

public class TempSkill {

    private Integer skillId;
    private String skillName;
    private String skillType;
    private SkillCategory skillCategory;
    private String skillCategoryUrl;
    private Integer skillAttack;
    private String skillDescription;
    private Integer pokemonId;
    private String pokemonName;
    private Integer bagId;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public SkillCategory getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(SkillCategory skillCategory) {
        this.skillCategory = skillCategory;
    }

    public String getSkillCategoryUrl() {
        return skillCategoryUrl;
    }

    public void setSkillCategoryUrl(String skillCategoryUrl) {
        this.skillCategoryUrl = skillCategoryUrl;
    }

    public Integer getSkillAttack() {
        return skillAttack;
    }

    public void setSkillAttack(Integer skillAttack) {
        this.skillAttack = skillAttack;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }
}
