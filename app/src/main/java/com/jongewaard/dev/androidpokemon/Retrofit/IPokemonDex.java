package com.jongewaard.dev.androidpokemon.Retrofit;

import com.jongewaard.dev.androidpokemon.model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {

    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
