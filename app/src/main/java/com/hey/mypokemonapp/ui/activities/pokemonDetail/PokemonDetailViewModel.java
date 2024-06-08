package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.data.repository.PokemonDetailRepository;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PokemonDetailViewModel extends ViewModel {
    private final PokemonDetailRepository repository;
    public MutableLiveData<PokemonDetail> pokemonDetailState = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();
    public MutableLiveData<Throwable> onErrorState = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable;

    private Pokemon pokemon;

    public PokemonDetailViewModel() {
        this.repository = new PokemonDetailRepository();
        this.compositeDisposable = new CompositeDisposable();
    }

    public void get(){
        if (pokemon == null) {
            onErrorState.setValue(new Throwable("No se encontraron datos"));
            return;
        }
        isLoadingState.setValue(true);
        Disposable disposable = repository.getDetail(pokemon.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonDetail -> {
                            pokemonDetailState.setValue(pokemonDetail);
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

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
