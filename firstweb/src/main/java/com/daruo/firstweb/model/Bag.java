package com.daruo.firstweb.model;

public class Bag {

    private Integer bagId;
    private Integer userId;
    private Integer pokemonId;
    private String pokemonNewName;

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
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

    public String getPokemonNewName() {
        return pokemonNewName;
    }

    public void setPokemonNewName(String pokemonNewName) {
        this.pokemonNewName = pokemonNewName;
    }
}
