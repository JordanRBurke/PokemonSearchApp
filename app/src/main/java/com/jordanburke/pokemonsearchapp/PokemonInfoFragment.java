package com.jordanburke.pokemonsearchapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokemonInfoFragment extends Fragment {

    private String basUrl = "http://pokeapi.co/api/v2/pokemon/";
    private Retrofit retrofit;
    private PokemonRetrofit pokemonRetrofit;
    @BindView(R.id.pokemon_text_view)
    protected TextView pokemonNameText;
    @BindView(R.id.pokemon_moves_text)
    protected TextView pokemonDescriptionText;
    public final static String POKEMON_NAME = "pokemon_name";
    public final static String POKEMON_DESCRIPTION = "pokemon_description";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public static PokemonInfoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PokemonInfoFragment fragment = new PokemonInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void buildRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(basUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonRetrofit = retrofit.create(PokemonRetrofit.class);

    }

    private void makeApiCall(String name) {
        pokemonRetrofit.getPokemonText(name).enqueue(new Callback<PokemonRetrofit.PokemonName>() {
            @Override
            public void onResponse(Call<PokemonRetrofit.PokemonName> call, Response<PokemonRetrofit.PokemonName> response) {
                if (response.isSuccessful()) {
                    pokemonNameText.setText(response.body().getPokemonName().toString());
                    pokemonDescriptionText.setText((CharSequence) response.body().getDescription());
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }//s
            }

            @Override
            public void onFailure(Call<PokemonRetrofit.PokemonName> call, Throwable t) {

            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        String name = getArguments().getString(POKEMON_NAME);

        buildRetrofit();
        makeApiCall(name);
    }
}
