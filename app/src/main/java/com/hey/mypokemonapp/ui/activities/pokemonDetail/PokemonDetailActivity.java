package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.hey.mypokemonapp.core.constant.Strings;
import com.hey.mypokemonapp.core.provider.ImageProvider;
import com.hey.mypokemonapp.core.utils.StringUtils;
import com.hey.mypokemonapp.databinding.ActivityPokemonDetailBinding;
import com.hey.mypokemonapp.domain.mapper.Mapper;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;
import com.hey.mypokemonapp.domain.model.detail.base.BaseDetailItem;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;
import com.hey.mypokemonapp.ui.recyclerAdapters.adapterSprites.RecyclerAdapterSprites;
import com.hey.mypokemonapp.ui.recyclerAdapters.adapterBaseDetail.RecyclerAdapterBaseDetail;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PokemonDetailActivity extends AppCompatActivity {

    private static final String ID_KEY = "pokemon_string";
    private ActivityPokemonDetailBinding binding;
    public PokemonDetailViewModel pokemonDetailViewModel;
    public Dialog dialogError;


    public static void start(AppCompatActivity activity, String content, ImageView imageView) {
        Intent starter = new Intent(activity, PokemonDetailActivity.class);
        starter.putExtra(ID_KEY, content);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, imageView.getTransitionName());
        activity.startActivity(starter, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSharedElement();
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setSharedElement() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().setSharedElementExitTransition(new ChangeBounds());
    }

    private void setup() {
        setViewModel();
        setUiConfig();
        pokemonDetailViewModel.get();
    }

    private void setViewModel() {
        pokemonDetailViewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);
        pokemonDetailViewModel.pokemonDetailState.observe(this, this::validateDetailPokemon);
        pokemonDetailViewModel.onErrorState.observe(this, this::validateError);
    }

    private void setUiConfig() {
        String content = getIntent().getStringExtra(ID_KEY);
        Pokemon pokemon = new Gson().fromJson(content, Pokemon.class);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        binding.imagePokemon.setTransitionName(Strings.IMAGE_TRANSITION + pokemon.getId());
        binding.tvNamePokemon.setText(StringUtils.formatName(pokemon.name));
        binding.tvIdPokemon.setText(Strings.NUMERAL.concat(" ").concat(String.valueOf(pokemon.getId())));
        ImageProvider.getImage(pokemon.getId(), binding.imagePokemon);
        pokemonDetailViewModel.setPokemon(pokemon);
    }


    private void validateDetailPokemon(PokemonDetail pokemonDetail) {
        ArrayList<BaseDetailItem> listType = Mapper.mapTypesMainToBase(pokemonDetail.typeArrayList);
        setBaseAdapterDetail(listType, binding.rvTypesPokemon);

        binding.weightPokemon.setText(String.valueOf(pokemonDetail.weight).concat(" ").concat("Kg"));

        RecyclerAdapterSprites adapterSprites = new RecyclerAdapterSprites();
        adapterSprites.updateList(pokemonDetail.sprites.toList());
        binding.rvSpritesPokemon.setAdapter(adapterSprites);

        ArrayList<BaseDetailItem> listAbilities = Mapper.mapAbilitiesMainToBase(pokemonDetail.abilitiesMainList);
        setBaseAdapterDetail(listAbilities, binding.rvAbilitiesPokemon);

        ArrayList<BaseDetailItem> listMove = Mapper.mapMovesMainToBase(pokemonDetail.movesMainArrayList);
        setBaseAdapterDetail(listMove, binding.rvMovesPokemon);
        binding.rvMovesPokemon.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
    }

    private void setBaseAdapterDetail(ArrayList<BaseDetailItem> typeArrayList, RecyclerView binding) {
        RecyclerAdapterBaseDetail adapterTypes = new RecyclerAdapterBaseDetail();
        adapterTypes.updateList(typeArrayList);
        binding.setAdapter(adapterTypes);
    }

    private void validateError(Throwable throwable) {
        if (dialogError != null) if (dialogError.isShowing()) dialogError.dismiss();
        dialogError = new MaterialAlertDialogBuilder(this)
                .setTitle("Error al obtener los datos")
                .setMessage("Causa: " + throwable.getLocalizedMessage())
                .setNegativeButton("Salir", (dialogInterface, i) -> finish())
                .setPositiveButton("Reintentar", (dialogInterface, i) -> {
                    pokemonDetailViewModel.get();
                }).create();
        dialogError.show();
    }

}