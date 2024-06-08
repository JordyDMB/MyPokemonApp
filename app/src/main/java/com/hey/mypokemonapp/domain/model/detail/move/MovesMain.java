package com.hey.mypokemonapp.domain.model.detail.move;

import com.google.gson.annotations.SerializedName;

public class MovesMain {

    @SerializedName("move")
    public Move move;

    @Override
    public String toString() {
        return "MovesMain{" +
                "move=" + move +
                '}';
    }
}
