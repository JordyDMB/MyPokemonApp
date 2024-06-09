package com.hey.mypokemonapp.ui.recyclerAdapters.adapterPokemon;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.R;
import com.hey.mypokemonapp.domain.model.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class RecyclerAdapterPokemon extends RecyclerView.Adapter<ViewHolderPokemon>{

    private final BiConsumer<Pokemon, ImageView> onClick;

    private final AsyncListDiffer<Pokemon> mDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);


    public RecyclerAdapterPokemon(BiConsumer<Pokemon, ImageView> onClick) {
        this.onClick = onClick;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(ArrayList<Pokemon> list) {
        mDiffer.submitList(list);
    }

    @NonNull
    @Override
    public ViewHolderPokemon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_pokemon, parent, false);
        return new ViewHolderPokemon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPokemon holder, int position) {
        Pokemon pokemon = mDiffer.getCurrentList().get(position);
        holder.setup(pokemon);
        holder.setOnClickListener(onClick);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public static final DiffUtil.ItemCallback<Pokemon> DIFF_CALLBACK = new DiffUtil.ItemCallback<Pokemon>() {

        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.name.equalsIgnoreCase(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.compare(newItem);
        }
    };

}
