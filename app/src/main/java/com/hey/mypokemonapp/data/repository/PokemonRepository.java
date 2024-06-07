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

    public MutableLiveData<PokemonResponse> getPokemonByLimits(int limit) {
        final MutableLiveData<PokemonResponse> data = new MutableLiveData<>();

        apiService.getListPokemon(limit).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.isSuccessful()){
                    data.postValue(response.body());
                }else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<PokemonResponse> getPokemonByLimits(int start, int limit) {
        final MutableLiveData<PokemonResponse> data = new MutableLiveData<>();
        apiService.getListPokemon(start, limit).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.isSuccessful()){
                    data.postValue(response.body());
                }else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }


    public Single<PokemonResponse> getPokemon(){
        return apiService.getListPokemon();
    }



}
