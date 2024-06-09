package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.core.constant.Strings;
import com.hey.mypokemonapp.data.repository.PokemonDetailRepository;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@HiltViewModel
public class PokemonDetailViewModel extends ViewModel {
    private final PokemonDetailRepository repository;
    public MutableLiveData<PokemonDetail> pokemonDetailState = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();
    public MutableLiveData<Throwable> onErrorState = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable;
    private Pokemon pokemon;

    @Inject
    public PokemonDetailViewModel(PokemonDetailRepository pokemonDetailRepository, CompositeDisposable compositeDisposable) {
        this.repository = pokemonDetailRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void get(){
        if (pokemon == null) {
            onErrorState.setValue(new Throwable(Strings.NO_VALUE_POKEMON));
            return;
        }
        if (pokemonDetailState.getValue() == null) {
            isLoadingState.setValue(true);
            Disposable disposable = repository.getDetail(pokemon.getId()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            pokemonDetail -> {
                                isLoadingState.setValue(false);
                                pokemonDetailState.setValue(pokemonDetail);
                            },
                            throwable -> {
                                isLoadingState.setValue(false);
                                onErrorState.setValue(throwable);
                            }
                    );
            compositeDisposable.add(disposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }


}
