package com.hey.mypokemonapp.ui.recyclerAdapters.adapterBaseDetail;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.R;
import com.hey.mypokemonapp.domain.model.detail.base.BaseDetailItem;

import java.util.ArrayList;

public class RecyclerAdapterBaseDetail extends RecyclerView.Adapter<ViewHolderBaseDetail>{


    private final AsyncListDiffer<BaseDetailItem> mDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);


    public RecyclerAdapterBaseDetail() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(ArrayList<BaseDetailItem> list) {
        mDiffer.submitList(list);
    }

    @NonNull
    @Override
    public ViewHolderBaseDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_detail_base, parent, false);
        return new ViewHolderBaseDetail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBaseDetail holder, int position) {
        BaseDetailItem baseDetailItem = mDiffer.getCurrentList().get(position);
        holder.setup(baseDetailItem);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public static final DiffUtil.ItemCallback<BaseDetailItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<BaseDetailItem>() {


        @Override
        public boolean areItemsTheSame(@NonNull BaseDetailItem oldItem, @NonNull BaseDetailItem newItem) {
            return oldItem.name.equalsIgnoreCase(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull BaseDetailItem oldItem, @NonNull BaseDetailItem newItem) {
            return false;
        }
    };

}
