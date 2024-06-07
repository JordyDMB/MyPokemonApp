package com.hey.mypokemonapp.core.provider;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageProvider {

    private static final String BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/";

    public static void getImage(int id, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(BASE_URL + id + ".png")
                .into(imageView);
    }

}
