package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.gson.Gson;
import com.hey.mypokemonapp.core.provider.ImageProvider;
import com.hey.mypokemonapp.core.utils.StringUtils;
import com.hey.mypokemonapp.databinding.ActivityPokemonDetailBinding;
import com.hey.mypokemonapp.domain.mapper.Mapper;
import com.hey.mypokemonapp.domain.model.detail.PokemonDetail;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;
import com.hey.mypokemonapp.ui.adapters.RecyclerAdapterSprites.RecyclerAdapterSprites;
import com.hey.mypokemonapp.ui.adapters.adapterAbilities.RecyclerAdapterBaseDetail;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String ID_KEY  = "content_string";
    private ActivityPokemonDetailBinding binding;
    private PokemonDetailViewModel pokemonDetailViewModel;


    public static void start(AppCompatActivity activity, String content, ImageView imageView) {
        Intent starter = new Intent(activity, PokemonDetailActivity.class);
        starter.putExtra(ID_KEY, content);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, imageView.getTransitionName());
        activity.startActivity(starter, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().setSharedElementExitTransition(new ChangeBounds());
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    private void setup() {
        pokemonDetailViewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);
        setImage();
        pokemonDetailViewModel.pokemonDetailState.observe(this, this::validateDetailPokemon );
        pokemonDetailViewModel.get();
    }

    private void setImage() {
        String content = getIntent().getStringExtra(ID_KEY);
        Pokemon pokemon = new Gson().fromJson(content, Pokemon.class);
        pokemonDetailViewModel.setPokemon(pokemon);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        binding.imagePokemon.setTransitionName("image_transition_" + pokemon.getId());
        binding.toolbar.setTitle(StringUtils.formatName(pokemon.name).concat(" - ID: ").concat(String.valueOf(pokemon.getId())));
        ImageProvider.getImage(pokemon.getId(), binding.imagePokemon);
    }


    private void validateDetailPokemon(PokemonDetail pokemonDetail){
        RecyclerAdapterBaseDetail adapterTypes = new RecyclerAdapterBaseDetail();
        adapterTypes.updateList(Mapper.mapTypesMainToBase(pokemonDetail.typeArrayList));
        binding.rvTypesPokemon.setAdapter(adapterTypes);

        binding.weightPokemon.setText(String.valueOf(pokemonDetail.weight).concat(" ").concat("Kg"));

        RecyclerAdapterSprites adapterSprites = new RecyclerAdapterSprites();
        adapterSprites.updateList(pokemonDetail.sprites.toList());
        binding.rvSpritesPokemon.setAdapter(adapterSprites);

        RecyclerAdapterBaseDetail adapterAbilities = new RecyclerAdapterBaseDetail();
        adapterAbilities.updateList(Mapper.mapAbilitiesMainToBase(pokemonDetail.abilitiesMainList));
        binding.rvAbilitiesPokemon.setAdapter(adapterAbilities);

        RecyclerAdapterBaseDetail adapterMoves = new RecyclerAdapterBaseDetail();
        adapterMoves.updateList(Mapper.mapMovesMainToBase(pokemonDetail.movesMainArrayList));
        binding.rvMovesPokemon.setAdapter(adapterMoves);

    }
}