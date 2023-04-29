package com.daruo.firstweb.dto;

public class TempShopCar {

    private Integer seqNo = 0;
    private Integer userId;
    private Integer pokemonId;
    private String pokemonName;
    private String imageUel;
    private Integer orderId;
    private Integer price;
    private Integer stock;
    private Integer buyCnt;
    private Integer amount;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getImageUel() {
        return imageUel;
    }

    public void setImageUel(String imageUel) {
        this.imageUel = imageUel;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
