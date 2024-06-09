package com.hey.mypokemonapp.ui.activities.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.hey.mypokemonapp.databinding.ActivityMainBinding;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;
import com.hey.mypokemonapp.ui.activities.pokemonDetail.PokemonDetailActivity;
import com.hey.mypokemonapp.ui.recyclerAdapters.adapterPokemon.RecyclerAdapterPokemon;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MainViewModel mainViewModel;
    private final RecyclerAdapterPokemon adapterPokemon = new RecyclerAdapterPokemon((pokemonModel, imageView) ->
            PokemonDetailActivity.start(this, pokemonModel.toJSON(), imageView));
    private Dialog dialogError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setup() {
        setViewModel();
        setUiConfig();
        mainViewModel.get();
    }

    private void setUiConfig() {
        binding.rvPokemon.setAdapter(adapterPokemon);
        binding.rvPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                assert layoutManager != null;
                int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                mainViewModel.validateSearchIfNeeded(lastVisibleItem);
            }
        });
        binding.btnRefresh.setOnClickListener(view -> refreshData());
    }

    private void setViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.pokemonDataState.observe(this, this::validateListPokemon);
        mainViewModel.isLoadingState.observe(this, this::observeProgressLoading);
        mainViewModel.onErrorState.observe(this,this::observeError);
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
        binding.lottie.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
    }

    private void observeError(Throwable throwable){
        if (dialogError != null) if (dialogError.isShowing()) return;
        dialogError = new MaterialAlertDialogBuilder(this)
                .setTitle("Error al obtener los datos")
                .setMessage("Causa: " + throwable.getLocalizedMessage())
                .setPositiveButton("Aceptar", null)
                .setNegativeButton("Reintentar", (dialogInterface, i) -> mainViewModel.retrySearch())
                .create();
        dialogError.show();
    }

}