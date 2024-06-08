package com.hey.mypokemonapp.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hey.mypokemonapp.core.provider.ImageProvider;
import com.hey.mypokemonapp.core.provider.RetrofitProvider;
import com.hey.mypokemonapp.data.interfaces.PokeApiService;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {

    private final PokeApiService apiService;

    public PokemonRepository() {
        apiService = RetrofitProvider.getInstance().getApi();
    }

    public Single<PokemonResponse> getPokemon(){
        return apiService.getListPokemon(40);
    }

    public Single<PokemonResponse> getPokemonByOffset(int offset, int limit){
        return apiService.getListPokemon(offset, limit);
    }



}
