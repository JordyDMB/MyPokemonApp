package com.hey.mypokemonapp.ui.activities.pokemonDetail;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.Window;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hey.mypokemonapp.R;
import com.hey.mypokemonapp.core.provider.ImageProvider;
import com.hey.mypokemonapp.databinding.ActivityPokemonDetailBinding;
import com.hey.mypokemonapp.ui.activities.main.MainViewModel;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String ID_KEY  = "id";
    private ActivityPokemonDetailBinding binding;
    private int id = 0;
    private PokemonDetailViewModel pokemonDetailViewModel;

    public static void start(AppCompatActivity activity, int id, ImageView imageView) {
        Intent starter = new Intent(activity, PokemonDetailActivity.class);
        starter.putExtra(ID_KEY, id);
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
        setImage();
        pokemonDetailViewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);
        pokemonDetailViewModel.get(id);
    }

    private void setImage() {
        id = getIntent().getIntExtra(ID_KEY, 0);
        binding.image.setTransitionName("image_transition_" + id);
        ImageProvider.getImage(id, binding.image);
    }
}