package com.daruo.firstweb.model;

import com.daruo.firstweb.constant.PokemonCategory;

public class Pokemon {

    private Integer pokemonId;
    private String pokemonName;
    private String imageUrl;
    private PokemonCategory category;
    private Integer life;
    private Integer exp;
    private Integer attack;
    private String skill;

    public Pokemon() {
    }

    public Pokemon(Integer pokemonId, String pokemonName, String imageUrl, PokemonCategory category, Integer life, Integer exp, Integer attack, String skill) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.imageUrl = imageUrl;
        this.category = category;
        this.life = life;
        this.exp = exp;
        this.attack = attack;
        this.skill = skill;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PokemonCategory getCategory() {
        return category;
    }

    public void setCategory(PokemonCategory category) {
        this.category = category;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
