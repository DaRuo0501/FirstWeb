package com.daruo.firstweb.dto;

import com.daruo.firstweb.constant.PokemonCategory;

import java.util.Date;

public class TempPokemon {

    private Integer myPokemonId;
    private Integer pokemonId;
    private String pokemonName;
    private String pokemonImageUrl;
    private PokemonCategory category;
    private Integer hp;
    private Integer lv;
    private Integer exp;
    private Integer attack;
    private Integer defense;
    private Integer speed;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getMyPokemonId() {
        return myPokemonId;
    }

    public void setMyPokemonId(Integer myPokemonId) {
        this.myPokemonId = myPokemonId;
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

    public String getPokemonImageUrl() {
        return pokemonImageUrl;
    }

    public void setPokemonImageUrl(String pokemonImageUrl) {
        this.pokemonImageUrl = pokemonImageUrl;
    }

    public PokemonCategory getCategory() {
        return category;
    }

    public void setCategory(PokemonCategory category) {
        this.category = category;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
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

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}