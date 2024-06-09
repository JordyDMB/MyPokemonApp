package com.hey.mypokemonapp.ui.recyclerAdapters.adapterSprites;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hey.mypokemonapp.R;
import com.hey.mypokemonapp.databinding.RowItemSpritesBinding;

public class ViewHolderSprite extends RecyclerView.ViewHolder {

    private final RowItemSpritesBinding binding;

    public ViewHolderSprite(@NonNull View itemView) {
        super(itemView);
        binding = RowItemSpritesBinding.bind(itemView);
    }

    public void setup(String sprite) {
        Glide.with(binding.image)
                .load(sprite)
                .placeholder(null)
                .error(R.drawable.ic_error_24)
                .into(binding.image);
    }

}
