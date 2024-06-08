package com.hey.mypokemonapp.ui.activities.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.data.repository.PokemonRepository;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {


    private final PokemonRepository pokemonRepository;
    public MutableLiveData<ArrayList<Pokemon>> pokemonDataState = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();
    public MutableLiveData<Throwable> onErrorState = new MutableLiveData<>();
    private String nextUrl = "";
    private final CompositeDisposable compositeDisposable;

    public MainViewModel() {
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
                        throwable -> onErrorState.setValue(throwable)
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void validateSearchIfNeeded( int lastVisibleItem) {

        boolean isLoading = Boolean.TRUE.equals(isLoadingState.getValue());
        if (isLoading){
            return;
        }
        if (pokemonDataState.getValue() == null) return;
        int totalItemCount = pokemonDataState.getValue().size();
        int visibleThreshold = 5;
        if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            if (nextUrl != null) {
                if (!nextUrl.isEmpty()) {
                    searchNext(totalItemCount);
                }
            }
        }
    }

    private void searchNext(int totalItemCount) {
        isLoadingState.setValue(true);
        Disposable disposable = pokemonRepository.getPokemonByOffset(totalItemCount, 40).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonResponse -> {
                            nextUrl = pokemonResponse.next;
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
            validateSearchIfNeeded(pokemonDataState.getValue().size());
    }

    public void refreshAllData() {
        int lastSize = pokemonDataState.getValue() == null ? 40 : pokemonDataState.getValue().size();
        isLoadingState.setValue(true);
        Disposable disposable = pokemonRepository.getPokemonByOffset(0, lastSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonResponse -> {
                            nextUrl = pokemonResponse.next;
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
}
