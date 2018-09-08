package com.jongewaard.dev.androidpokemon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jongewaard.dev.androidpokemon.Adapter.PokemonListAdapter;
import com.jongewaard.dev.androidpokemon.Retrofit.IPokemonDex;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonType extends Fragment {


     RecyclerView pokemon_list_recyclerview;

    PokemonListAdapter adapter, search_adapter;
    List<String> last_suggest = new ArrayList<>();

    MaterialSearchBar searchBar;

    static PokemonType instance;

    public static PokemonType getInstance() {
        if(instance == null)
            instance = new PokemonType();
        return instance;
    }

    public PokemonType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_type, container, false);
    }

}
