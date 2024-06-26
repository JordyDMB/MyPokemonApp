package com.hey.mypokemonapp.ui.recyclerAdapters.adapterBaseDetail;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hey.mypokemonapp.core.utils.StringUtils;
import com.hey.mypokemonapp.databinding.RowItemDetailBaseBinding;
import com.hey.mypokemonapp.domain.model.detail.base.BaseDetailItem;

public class ViewHolderBaseDetail extends RecyclerView.ViewHolder {

    private final RowItemDetailBaseBinding binding;

    public ViewHolderBaseDetail(@NonNull View itemView) {
        super(itemView);
        binding = RowItemDetailBaseBinding.bind(itemView);
    }

    public void setup(BaseDetailItem baseDetailItem) {
        binding.tvName.setText(StringUtils.formatName(baseDetailItem.name));
    }

}
