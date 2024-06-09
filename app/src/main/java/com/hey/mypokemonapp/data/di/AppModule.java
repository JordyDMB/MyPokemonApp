package com.hey.mypokemonapp.data.di;

import com.hey.mypokemonapp.data.interfaces.PokeApiService;
import com.hey.mypokemonapp.data.interfaces.PokeDetailApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    private static final String BASE_URL  = "https://pokeapi.co/api/v2/";

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    PokeApiService getApi(Retrofit retrofit) {
        return retrofit.create(PokeApiService.class);
    }

    @Singleton
    @Provides
    PokeDetailApiService getDetailApi(Retrofit retrofit){
        return retrofit.create(PokeDetailApiService.class);
    }
}
