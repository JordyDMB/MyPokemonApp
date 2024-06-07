package com.hey.mypokemonapp.domain.model.pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonResponse {

    @SerializedName("next")
    public String next = "";

    @SerializedName("count")
    public int count = 0;

    @SerializedName("results")
    public ArrayList<PokemonModel> listPokemon = new ArrayList<>();


    @Override
    public String toString() {
        return "PokemonResponse{" +
                "next='" + next + '\'' +
                ", count=" + count +
                ", listPokemon=" + listPokemon +
                '}';
    }
}
