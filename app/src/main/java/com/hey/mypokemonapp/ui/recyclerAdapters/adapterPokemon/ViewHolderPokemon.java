package com.hey.mypokemonapp.ui.recyclerAdapters.adapterPokemon;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.core.constant.Strings;
import com.hey.mypokemonapp.core.provider.ImageProvider;
import com.hey.mypokemonapp.core.utils.StringUtils;
import com.hey.mypokemonapp.databinding.RowItemPokemonBinding;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;

import java.util.function.BiConsumer;

public class ViewHolderPokemon extends RecyclerView.ViewHolder {

    private static final String TEXT_ID = "ID:";
    private final RowItemPokemonBinding binding;
    private Pokemon pokemon;

    public ViewHolderPokemon(@NonNull View itemView) {
        super(itemView);
        binding = RowItemPokemonBinding.bind(itemView);
    }

    public void setup(Pokemon pokemon) {
        this.pokemon = pokemon;

        binding.tvNamePokemon.setText(StringUtils.formatName(pokemon.name));

        if (pokemon.getId() != 0) {
            String idPokemon = TEXT_ID.concat(" ").concat(String.valueOf(pokemon.getId()));
            binding.tvIdPokemon.setText(idPokemon);
            ImageProvider.getImage(pokemon.getId(), binding.shapeableImageViewPokemon);
            binding.shapeableImageViewPokemon.setTransitionName(Strings.IMAGE_TRANSITION + pokemon.getId());
        }else {
            binding.tvIdPokemon.setVisibility(View.GONE);
        }
    }

    public void setOnClickListener(BiConsumer<Pokemon, ImageView> onClick) {
        itemView.setOnClickListener(v -> onClick.accept(this.pokemon, binding.shapeableImageViewPokemon));
    }
}
