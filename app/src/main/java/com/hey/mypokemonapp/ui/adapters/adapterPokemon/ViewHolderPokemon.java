package com.hey.mypokemonapp.ui.adapters.adapterPokemon;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.core.provider.ImageProvider;
import com.hey.mypokemonapp.databinding.RowItemPokemonBinding;
import com.hey.mypokemonapp.domain.model.pokemon.PokemonModel;

import java.util.function.BiConsumer;

public class ViewHolderPokemon extends RecyclerView.ViewHolder {

    private static final String TEXT_ID = "ID:";
    private final RowItemPokemonBinding binding;
    private PokemonModel pokemonModel;

    public ViewHolderPokemon(@NonNull View itemView) {
        super(itemView);
        binding = RowItemPokemonBinding.bind(itemView);
    }

    public void setup(PokemonModel pokemonModel) {
        this.pokemonModel = pokemonModel;
        binding.tvNamePokemon.setText(pokemonModel.name);
        if (pokemonModel.getIdPokemon() != 0) {
            String idPokemon = TEXT_ID.concat(" ").concat(String.valueOf(pokemonModel.getIdPokemon()));
            binding.tvIdPokemon.setText(idPokemon);
            ImageProvider.getImage(pokemonModel.getIdPokemon(), binding.shapeableImageViewPokemon);
            binding.shapeableImageViewPokemon.setTransitionName("image_transition_" + pokemonModel.getIdPokemon());

        }else {
            binding.tvIdPokemon.setVisibility(View.GONE);
        }
    }

    public void setOnClickListener(BiConsumer<PokemonModel, ImageView> onClick) {
        itemView.setOnClickListener(v -> onClick.accept(this.pokemonModel, binding.shapeableImageViewPokemon));
    }
}
