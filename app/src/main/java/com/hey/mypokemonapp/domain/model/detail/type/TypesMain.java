package com.hey.mypokemonapp.domain.model.detail.type;

import com.google.gson.annotations.SerializedName;

public class TypesMain {

    @SerializedName("slot")
    public int slot = 0;

    @SerializedName("type")
    public Type type;

    @Override
    public String toString() {
        return "TypesMain{" +
                "slot=" + slot +
                ", type=" + type +
                '}';
    }
}
