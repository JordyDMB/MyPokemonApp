package com.hey.mypokemonapp.data.interfaces;

import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeDetailApiService {

    @GET("pokemon/{id}")
    Single<PokemonDetail> getDetail(@Path("id") int limit);

}
