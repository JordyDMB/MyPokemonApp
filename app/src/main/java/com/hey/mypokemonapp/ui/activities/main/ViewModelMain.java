package com.hey.mypokemonapp.ui.activities.main;

import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.data.repository.PokemonRepository;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonModel;

import java.util.ArrayList;
import java.util.logging.Handler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ViewModelMain extends ViewModel {


    private int visibleThreshold = 5;
    private PokemonRepository pokemonRepository;
    public MutableLiveData<ArrayList<PokemonModel>> pokemonDataState = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();

    private String nextUrl = "";

    private final CompositeDisposable compositeDisposable;

    public ViewModelMain() {
        compositeDisposable = new CompositeDisposable();
        pokemonRepository = new PokemonRepository();
    }


    public void get(){
        Disposable disposable = pokemonRepository.getPokemon().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonResponse -> {
                            nextUrl = pokemonResponse.next;
                            pokemonDataState.setValue(pokemonResponse.listPokemon);
                        },
                        throwable -> {
                            Log.v("revisar", throwable.toString());
                        }
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void validateSearchIfNeeded(int totalItemCount, int lastVisibleItem) {
        boolean isLoading = Boolean.TRUE.equals(isLoadingState.getValue());
        if (isLoading){
            return;
        }
        isLoadingState.setValue(true);
        if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            Disposable disposable = pokemonRepository.getPokemon().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            pokemonResponse -> {
                                nextUrl = pokemonResponse.next;
                                ArrayList<PokemonModel> list = pokemonDataState.getValue();
                                list.addAll(pokemonResponse.listPokemon);
                                isLoadingState.setValue(false);
                            },
                            throwable -> {
                                isLoadingState.setValue(false);
                            }
                    );
            compositeDisposable.add(disposable);
        }


    }
}
