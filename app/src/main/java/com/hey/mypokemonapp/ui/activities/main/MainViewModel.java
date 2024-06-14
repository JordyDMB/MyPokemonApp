package com.hey.mypokemonapp.ui.activities.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.data.repository.PokemonRepository;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {


    private final PokemonRepository pokemonRepository;
    public MutableLiveData<ArrayList<Pokemon>> pokemonDataState = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();
    public MutableLiveData<Throwable> onErrorState = new MutableLiveData<>();
//    private String nextUrl = "";
    private final CompositeDisposable compositeDisposable;

    private PokemonResponse lastResponse;

    @Inject
    public MainViewModel(PokemonRepository pokemonRepository,CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        this.pokemonRepository = pokemonRepository;
    }


    public void get(){
        if (pokemonDataState.getValue() == null) {
            Disposable disposable = pokemonRepository.getFirstListPokemon().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            pokemonResponse -> {
                                this.lastResponse = pokemonResponse;
                                pokemonDataState.setValue(pokemonResponse.listPokemon);
                            },
                            throwable -> onErrorState.setValue(throwable)
                    );
            compositeDisposable.add(disposable);
        }
    }

    public void validateSearchIfNeeded( int lastVisibleItem) {
        boolean isLoading = Boolean.TRUE.equals(isLoadingState.getValue());
        if (isLoading){
            return;
        }
        if (pokemonDataState.getValue() == null) return;
        int totalItemCount = pokemonDataState.getValue().size();
        if ((totalItemCount-1) == lastVisibleItem) {
            if (lastResponse.next != null) {
                if (!lastResponse.next.isEmpty()) {
                    searchNextList(totalItemCount);
                }
            }
        }
    }

    private void searchNextList(int totalItemCount) {
        isLoadingState.setValue(true);
        Disposable disposable = pokemonRepository.getListPokemonByOffsetAndLimit(totalItemCount, 40)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonResponse -> {
                            lastResponse = pokemonResponse;
                            ArrayList<Pokemon> list = pokemonDataState.getValue();
                            assert list != null;
                            list.addAll(pokemonResponse.listPokemon);
                            isLoadingState.setValue(false);
                            pokemonDataState.setValue(list);
                        },
                        throwable -> {
                            isLoadingState.setValue(false);
                            onErrorState.setValue(throwable);
                        }
                );
        compositeDisposable.add(disposable);
    }


    public void retrySearch() {
        if (pokemonDataState.getValue() == null)
            get();
        else
            validateSearchIfNeeded(pokemonDataState.getValue().size()-1);
    }

    public void refreshAllData() {
        int lastSize = pokemonDataState.getValue() == null ? 40 : pokemonDataState.getValue().size();
        isLoadingState.setValue(true);
        Disposable disposable = pokemonRepository.getListPokemonByOffsetAndLimit(0, lastSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonResponse -> {
                            lastResponse = pokemonResponse;
                            isLoadingState.setValue(false);
                            pokemonDataState.setValue(pokemonResponse.listPokemon);
                        },
                        throwable -> {
                            isLoadingState.setValue(false);
                            onErrorState.setValue(throwable);
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
