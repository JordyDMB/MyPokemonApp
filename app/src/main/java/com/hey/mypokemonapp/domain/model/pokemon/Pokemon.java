package com.hey.mypokemonapp.domain.model.pokemon;

import com.google.gson.annotations.SerializedName;

public class Pokemon implements Comparable<Pokemon> {

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
    public int compareTo(Pokemon o) {
        return 0 ;
    }


    public boolean compare(Pokemon pokemon){
        return name.equalsIgnoreCase(pokemon.name) && url.equalsIgnoreCase(pokemon.url);
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
