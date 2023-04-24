package com.daruo.firstweb.dto;

public class TempPokemon {

    private Integer pokemonId;
    private String pokemonName;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private Integer buyCnt;

    public TempPokemon() {
    }

    public TempPokemon(Integer pokemonId, String pokemonName, String imageUrl, Integer price, Integer stock, Integer buyCnt) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.buyCnt = buyCnt;
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
}
