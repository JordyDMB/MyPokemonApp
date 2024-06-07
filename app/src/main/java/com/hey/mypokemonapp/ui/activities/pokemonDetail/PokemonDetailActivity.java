package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hey.mypokemonapp.R;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String ID_KEY  = "id";

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, PokemonDetailActivity.class);
        starter.putExtra(ID_KEY, id);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}