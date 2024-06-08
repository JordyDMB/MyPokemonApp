package com.hey.mypokemonapp.domain.model.detail.base;

import com.google.gson.annotations.SerializedName;

public class BaseDetailItem {
    @SerializedName("name")
    public String name = "";

    @SerializedName("url")
    public String url = "";

    @Override
    public String toString() {
        return "BaseDetailItem{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
