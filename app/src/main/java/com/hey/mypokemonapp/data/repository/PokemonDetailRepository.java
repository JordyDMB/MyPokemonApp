package com.hey.mypokemonapp.data.repository;

import com.hey.mypokemonapp.data.interfaces.PokeDetailApiService;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;

import javax.inject.Inject;

import io.reactivex.Single;

public class PokemonDetailRepository {

    private final PokeDetailApiService apiService;

    @Inject
    public PokemonDetailRepository(PokeDetailApiService apiService) {
        this.apiService = apiService;
    }

    public Single<PokemonDetail> getDetail(int id){
        return apiService.getDetail(id);
    }

}
