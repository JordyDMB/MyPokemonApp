package com.hey.mypokemonapp.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hey.mypokemonapp.core.provider.RetrofitProvider;
import com.hey.mypokemonapp.data.interfaces.PokeApiService;
import com.hey.mypokemonapp.data.interfaces.PokeDetailApiService;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailRepository {

    private final PokeDetailApiService apiService;

    public PokemonDetailRepository() {
        apiService = RetrofitProvider.getInstance().getDetailApi();
    }

    public Single<PokemonDetail> getDetail(int id){
        return apiService.getDetail(id);
    }

}
