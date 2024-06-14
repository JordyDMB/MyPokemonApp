package com.hey.mypokemonapp.data.interfaces;

import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeDetailApiService {

    @GET("pokemon/{id}")
    Single<PokemonDetail> getDetail(@Path("id") int limit);

}
