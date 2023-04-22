package com.daruo.firstweb.model;

import com.daruo.firstweb.constant.PokemonCategory;

import java.util.Date;

public class Pokemon {

    private Integer pokemonId;
    private String pokemonName;
    private String imageUrl;
    private PokemonCategory category;
    private Integer life;
    private Integer exp;
    private Integer attack;
    private String skill1;
    private String skill2;
    private String skill3;
    private String skill4;
    private Integer price;
    private Integer stock;
    private Integer buyCnt;
    private Date createdDate;
    private Date lastModifiedDate;

    public Pokemon() {
    }

    public Pokemon(Integer pokemonId, String pokemonName, String imageUrl,
                   PokemonCategory category, Integer life, Integer exp, Integer attack,
                   String skill1, String skill2, String skill3, String skill4,
                   Integer price, Integer stock,
                   Date createdDate, Date lastModifiedDate) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.imageUrl = imageUrl;
        this.category = category;
        this.life = life;
        this.exp = exp;
        this.attack = attack;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
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

    public Integer getBuyCnt() {
        return buyCnt;
    }

    public void setBuyCnt(Integer buyCnt) {
        this.buyCnt = buyCnt;
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

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", pokemonName='" + pokemonName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", life=" + life +
                ", exp=" + exp +
                ", attack=" + attack +
                ", skill1='" + skill1 + '\'' +
                ", skill2='" + skill2 + '\'' +
                ", skill3='" + skill3 + '\'' +
                ", skill4='" + skill4 + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
