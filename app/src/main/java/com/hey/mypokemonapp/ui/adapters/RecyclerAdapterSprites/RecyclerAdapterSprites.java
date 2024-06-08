package com.hey.mypokemonapp.ui.adapters.RecyclerAdapterSprites;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.R;

import java.util.ArrayList;

public class RecyclerAdapterSprites extends RecyclerView.Adapter<ViewHolderSprite>{

    private final AsyncListDiffer<String> mDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);

    public RecyclerAdapterSprites() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(ArrayList<String> list) {
        mDiffer.submitList(list);
    }

    @NonNull
    @Override
    public ViewHolderSprite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_sprites, parent, false);
        return new ViewHolderSprite(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSprite holder, int position) {
        String sprite = mDiffer.getCurrentList().get(position);
        holder.setup(sprite);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public static final DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<String>() {


        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equalsIgnoreCase(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equalsIgnoreCase(newItem);
        }
    };

}
