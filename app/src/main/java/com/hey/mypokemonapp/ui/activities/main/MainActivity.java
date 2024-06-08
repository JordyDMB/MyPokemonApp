package com.hey.mypokemonapp.ui.activities.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.hey.mypokemonapp.databinding.ActivityMainBinding;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;
import com.hey.mypokemonapp.ui.activities.pokemonDetail.PokemonDetailActivity;
import com.hey.mypokemonapp.ui.adapters.adapterPokemon.RecyclerAdapterPokemon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    private final RecyclerAdapterPokemon adapterPokemon = new RecyclerAdapterPokemon((pokemonModel, imageView) ->
            PokemonDetailActivity.start(this, pokemonModel.toJSON(), imageView));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setup() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.rvPokemon.setAdapter(adapterPokemon);
        mainViewModel.pokemonDataState.observe(this, this::validateListPokemon);
        mainViewModel.isLoadingState.observe(this, this::observeProgressLoading);
        mainViewModel.onErrorState.observe(this,this::observeError);
        binding.rvPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                assert layoutManager != null;
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                mainViewModel.validateSearchIfNeeded(lastVisibleItem);
            }
        });
        binding.btnRefresh.setOnClickListener(view -> {
            refreshData();
        });
        mainViewModel.get();
    }

    @SuppressLint("SetTextI18n")
    private void validateListPokemon(ArrayList<Pokemon> list){
        binding.rvPokemon.setVisibility(View.VISIBLE);
        adapterPokemon.updateList(list);
        binding.textView.setText("Listado de pokemon disponibles: " + list.size());
    }

    private void refreshData() {
        binding.rvPokemon.setVisibility(View.GONE);
        mainViewModel.refreshAllData();
    }

    private void observeProgressLoading(Boolean aBoolean) {
        if (aBoolean){
            binding.lottie.setVisibility(View.VISIBLE);
        }else {
            binding.lottie.setVisibility(View.GONE);
        }
    }

    private void observeError(Throwable throwable){
        new MaterialAlertDialogBuilder(this)
                .setTitle("Error al obtener los datos")
                .setMessage("Causa: " + throwable.getLocalizedMessage())
                .setPositiveButton("Aceptar", null)
                .setNegativeButton("Reintentar", (dialogInterface, i) -> {
                    mainViewModel.retrySearch();
                })
                .show();
    }

}