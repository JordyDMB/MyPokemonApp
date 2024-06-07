package com.hey.mypokemonapp.domain.model.pokemon;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class PokemonModel implements Comparable<PokemonModel> {

    private int id = 0;

    @SerializedName("name")
    public String name = "";

    @SerializedName("url")
    public String url = "";

    public int getIdPokemon(){
        if (id == 0){
            String[] splitUrl = url.split("/");
            String lastSplit = splitUrl[splitUrl.length -1];
            try {
                id = Integer.parseInt(lastSplit);
            }catch (Exception ignore){}
        }
        return id;
    }

    @Override
    public int compareTo(PokemonModel o) {
        return 0 ;
    }


    public boolean compare(PokemonModel pokemonModel){
        return name.equalsIgnoreCase(pokemonModel.name) && url.equalsIgnoreCase(pokemonModel.url);
    }


    @Override
    public String toString() {
        return "PokemonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
