package com.hey.mypokemonapp.core.provider;

import com.hey.mypokemonapp.data.interfaces.PokeApiService;
import com.hey.mypokemonapp.data.interfaces.PokeDetailApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static final String BASE_URL  = "https://pokeapi.co/api/v2/";
    private static RetrofitProvider instance;
    private final Retrofit retrofit;

    private RetrofitProvider() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static synchronized RetrofitProvider getInstance() {
        if (instance == null) {
            instance = new RetrofitProvider();
        }
        return instance;
    }

    public PokeApiService getApi() {
        return retrofit.create(PokeApiService.class);
    }

    public PokeDetailApiService getDetailApi(){
        return retrofit.create(PokeDetailApiService.class);
    }
}
