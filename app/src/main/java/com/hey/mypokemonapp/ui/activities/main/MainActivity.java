package com.hey.mypokemonapp.ui.activities.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.databinding.ActivityMainBinding;
import com.hey.mypokemonapp.ui.activities.pokemonDetail.PokemonDetailActivity;
import com.hey.mypokemonapp.ui.adapters.adapterPokemon.RecyclerAdapterPokemon;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewModelMain viewModelMain;

    private final RecyclerAdapterPokemon adapterPokemon = new RecyclerAdapterPokemon((pokemonModel, imageView) ->
            PokemonDetailActivity.start(this, pokemonModel.getIdPokemon(), imageView));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setup() {
        viewModelMain = new ViewModelProvider(this).get(ViewModelMain.class);
        binding.rvPokemon.setAdapter(adapterPokemon);
        binding.rvPokemon.setLayoutManager(new LinearLayoutManager(this));
        viewModelMain.pokemonDataState.observe(this, adapterPokemon::updateList);
        viewModelMain.get();
        binding.rvPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                assert layoutManager != null;
                int totalItemCount = adapterPokemon.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                viewModelMain.validateSearchIfNeeded(totalItemCount, lastVisibleItem);
            }
        });
    }

}