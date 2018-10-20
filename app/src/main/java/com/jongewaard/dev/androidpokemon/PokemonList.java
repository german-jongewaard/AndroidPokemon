package com.jongewaard.dev.androidpokemon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jongewaard.dev.androidpokemon.Adapter.PokemonListAdapter;
import com.jongewaard.dev.androidpokemon.Common.Common;
import com.jongewaard.dev.androidpokemon.Common.ItemOffsetDecoration;
import com.jongewaard.dev.androidpokemon.Retrofit.IPokemonDex;
import com.jongewaard.dev.androidpokemon.Retrofit.RetrofitClient;
import com.jongewaard.dev.androidpokemon.model.Pokedex;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {

    IPokemonDex mIPokemonDex;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;

    static PokemonList instance;
    
    public static PokemonList getInstance() {
        if(instance == null)
            instance = new PokemonList();
        return instance;
    }


    public PokemonList() {

        Retrofit retrofit = RetrofitClient.getInstance();
        mIPokemonDex = retrofit.create(IPokemonDex.class);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        pokemon_list_recyclerview = (RecyclerView)view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //Decoration
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();

        return view;
    }

    private void fetchData() {
        mCompositeDisposable.add(mIPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);

                        pokemon_list_recyclerview.setAdapter(adapter);
                    }
                })
        );
    }
}
