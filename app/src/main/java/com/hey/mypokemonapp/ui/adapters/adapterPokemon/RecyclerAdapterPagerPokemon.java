package com.hey.mypokemonapp.ui.adapters.adapterPokemon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.hey.mypokemonapp.core.provider.RetrofitProvider;
import com.hey.mypokemonapp.data.interfaces.PokeApiService;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;

import io.reactivex.Single;
import kotlin.coroutines.Continuation;

public class RecyclerAdapterPagerPokemon extends PagingSource<Integer, Pokemon> {

    private final PokeApiService pokeApiService;

    public RecyclerAdapterPagerPokemon(){
        pokeApiService = RetrofitProvider.getInstance().getApi();
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Pokemon> pagingState) {
        return null;
    }

    @Nullable
    @Override
    public Single<PokemonResponse> load(@NonNull LoadParams<Integer> loadParams, @NonNull Continuation<? super LoadResult<Integer, Pokemon>> continuation) {
        int currentPage = loadParams.getKey() != null ? loadParams.getKey() : 40;
        int limit = 40;
        int offset = currentPage + limit;
        return pokeApiService.getListPokemon(offset, limit);
    }
}
