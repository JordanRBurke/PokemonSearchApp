package com.jordanburke.pokemonsearchapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_button)
    protected Button searchButton;
    @BindView(R.id.pokemon_edit_text)
    protected TextInputEditText pokemonEditText;

    private PokemonInfoFragment pokemonInfoFragment;
    public final static String POKEMON_NAME = "pokemon_name";
    public final static String POKEMON_DESCRIPTION = "pokemon_description";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.search_button)
    protected void searchButtonClicked() {

        pokemonInfoFragment = PokemonInfoFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(POKEMON_NAME, pokemonEditText.getText().toString());
        pokemonInfoFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, pokemonInfoFragment).commit();

    }


}
