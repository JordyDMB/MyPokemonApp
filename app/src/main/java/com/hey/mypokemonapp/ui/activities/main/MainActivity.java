package com.hey.mypokemonapp.ui.activities.main;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hey.mypokemonapp.databinding.ActivityMainBinding;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonModel;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonResponse;
import com.hey.mypokemonapp.ui.activities.pokemonDetail.PokemonDetailActivity;
import com.hey.mypokemonapp.ui.adapters.adapterPokemon.RecyclerAdapterPokemon;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewModelMain viewModelMain;

    private final RecyclerAdapterPokemon adapterPokemon = new RecyclerAdapterPokemon(pokemonModel ->
            PokemonDetailActivity.start(this, pokemonModel.getIdPokemon()));

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
        viewModelMain.pokemonData.observe(this, adapterPokemon::updateList);
        viewModelMain.get();
    }

}