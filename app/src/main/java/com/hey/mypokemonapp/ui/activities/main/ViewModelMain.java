package com.hey.mypokemonapp.ui.activities.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.data.repository.PokemonRepository;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonModel;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import java.util.ArrayList;
import java.util.StringTokenizer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ViewModelMain extends ViewModel {


    private PokemonRepository pokemonRepository;
    public MutableLiveData<ArrayList<PokemonModel>> pokemonData = new MutableLiveData<>();

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
                            pokemonData.setValue(pokemonResponse.listPokemon);
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

}
