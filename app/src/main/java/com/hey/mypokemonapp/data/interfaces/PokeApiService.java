package com.hey.mypokemonapp.data.interfaces;

import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonResponse> getListPokemonA(@Query("limit") int limit);

    @GET("pokemon")
    Call<PokemonResponse> getListPokemonA(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon")
    Single<PokemonResponse> getListPokemon(@Query("limit") int limit);

    @GET("pokemon")
    Single<PokemonResponse> getListPokemon(@Query("offset") int offset, @Query("limit") int limit);


}
