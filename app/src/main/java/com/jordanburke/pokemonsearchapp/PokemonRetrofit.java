package com.jordanburke.pokemonsearchapp;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PokemonRetrofit {
    @GET("{name}")
    Call<PokemonName> getPokemonText(@Path("name") String pokemonName);

    class PokemonName {

        @SerializedName("name")
        private String pokemonName;

        private String pokemonMove;
        @SerializedName("descriptions")
        private Description description;

        public String getPokemonName() {
            return pokemonName;
        }

        public Description getDescription() {
            return description;
        }

        class Description {

            @SerializedName("description")
            private String pokemonDescription;
        }
    }

}
