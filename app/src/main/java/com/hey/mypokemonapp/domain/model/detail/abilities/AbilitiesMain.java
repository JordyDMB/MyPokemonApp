package com.hey.mypokemonapp.domain.model.detail.abilities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AbilitiesMain {
    @SerializedName("is_hidden")
    public boolean isHidden = false;

    @SerializedName("slot")
    public int slot = 0;

    @SerializedName("ability")
    public Ability ability;

    @Override
    public String toString() {
        return "AbilitiesMain{" +
                "isHidden=" + isHidden +
                ", slot=" + slot +
                ", ability=" + ability +
                '}';
    }
}
