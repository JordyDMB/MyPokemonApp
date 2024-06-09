package com.hey.mypokemonapp.data.repository;

import com.hey.mypokemonapp.data.interfaces.PokeApiService;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class PokemonRepository {

    private final PokeApiService apiService;

    @Inject
    public PokemonRepository(PokeApiService apiService) {
        this.apiService = apiService;
    }

    public Single<PokemonResponse> getFirstListPokemon(){
        return apiService.getListPokemon(40);
    }

    public Single<PokemonResponse> getListPokemonByOffsetAndLimit(int offset, int limit){
        return apiService.getListPokemon(offset, limit);
    }



}
