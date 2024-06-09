package com.hey.mypokemonapp.core.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hey.mypokemonapp.R;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageProvider {
    private static final String BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/";

    public static void getImage(int id, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(BASE_URL + id + ".png")
                .into(imageView);
    }

    public static void getBitmap(Context context, int id, Consumer<Bitmap> onBitmap){
        Glide.with(context)
                .asBitmap()
                .load(BASE_URL + id + ".png")
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        onBitmap.accept(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        onBitmap.accept(null);
                    }
                });
    }

}
