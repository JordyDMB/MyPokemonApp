package com.hey.mypokemonapp.domain.model.detail.sprites;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Sprites {

    @SerializedName("back_default")
    public String backDefault = "";

    @SerializedName("back_female")
    public String backFemale = "";

    @SerializedName("back_shiny")
    public String backShiny = "";

    @SerializedName("back_shiny_female")
    public String backShinyFemale = "";

    @SerializedName("front_default")
    public String frontDefault = "";

    @SerializedName("front_female")
    public String frontFemale = "";

    @SerializedName("front_shiny")
    public String frontShiny = "";

    @SerializedName("front_shiny_female")
    public String frontShinyFemale = "";


    public ArrayList<String> toList(){
        ArrayList<String> list= new ArrayList<>();
        list.add(backDefault);
        list.add(backFemale);
        list.add(backShinyFemale);
        list.add(backShiny);
        list.add(frontFemale);
        list.add(frontShinyFemale);
        list.add(frontDefault);
        list.add(frontShiny);
        return list;
    }


    @Override
    public String toString() {
        return "Sprites{" +
                "backDefault='" + backDefault + '\'' +
                ", backFemale='" + backFemale + '\'' +
                ", backShiny='" + backShiny + '\'' +
                ", backShinyFemale='" + backShinyFemale + '\'' +
                ", frontDefault='" + frontDefault + '\'' +
                ", frontFemale='" + frontFemale + '\'' +
                ", frontShiny='" + frontShiny + '\'' +
                ", frontShinyFemale='" + frontShinyFemale + '\'' +
                '}';
    }
}
