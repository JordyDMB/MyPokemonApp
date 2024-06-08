package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hey.mypokemonapp.data.repository.PokemonDetailRepository;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;

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


    public PokemonDetailViewModel() {
        this.repository = new PokemonDetailRepository();
        this.compositeDisposable = new CompositeDisposable();
    }

    public void get(int id){
        isLoadingState.setValue(true);
        Log.v("revisar", "" + id);
        Disposable disposable = repository.getDetail(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        pokemonDetail -> {
                            Log.v("revisar", pokemonDetail.toString());
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
}
