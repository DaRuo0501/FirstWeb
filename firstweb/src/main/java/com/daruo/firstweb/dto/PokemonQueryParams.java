package com.daruo.firstweb.dto;

import com.daruo.firstweb.constant.PokemonCategory;

public class PokemonQueryParams {

    private PokemonCategory pokemonCategory;
    private String search;
    private String orderBy;
    private String sort;
    private Integer limit;
    private Integer offset;

    public PokemonCategory getPokemonCategory() {
        return pokemonCategory;
    }

    public void setPokemonCategory(PokemonCategory pokemonCategory) {
        this.pokemonCategory = pokemonCategory;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
