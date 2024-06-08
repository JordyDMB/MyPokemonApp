package com.hey.mypokemonapp.domain.model.detail;

import com.google.gson.annotations.SerializedName;
import com.hey.mypokemonapp.domain.model.detail.abilities.AbilitiesMain;
import com.hey.mypokemonapp.domain.model.detail.abilities.Ability;
import com.hey.mypokemonapp.domain.model.detail.move.MovesMain;
import com.hey.mypokemonapp.domain.model.detail.type.TypesMain;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;

import java.util.ArrayList;

public class PokemonDetail extends Pokemon{


    @SerializedName("types")
    public ArrayList<TypesMain> typeArrayList = new ArrayList<>();

    @SerializedName("weight")
    public Double weight = 0.0;

    @SerializedName("abilities")
    public ArrayList<AbilitiesMain> abilitiesMainList = new ArrayList<>();

    @SerializedName("moves")
    public ArrayList<MovesMain> movesMainArrayList = new ArrayList<>();


    @Override
    public String toString() {
        return "PokemonDetail{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", typeArrayList=" + typeArrayList +
                ", weight=" + weight +
                ", abilitiesMainList=" + abilitiesMainList +
                ", movesMainArrayList=" + movesMainArrayList +
                '}';
    }
}
