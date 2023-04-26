package com.daruo.firstweb.model;

public class ShopCar {

    private Integer seqNo = 0;
    private Integer userId;
    private Integer pokemonId;
    private Integer orderId;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
