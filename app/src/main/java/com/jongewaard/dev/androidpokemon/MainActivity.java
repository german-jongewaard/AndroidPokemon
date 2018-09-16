package com.jongewaard.dev.androidpokemon;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jongewaard.dev.androidpokemon.Common.Common;
import com.jongewaard.dev.androidpokemon.model.Pokemon;


public class MainActivity extends AppCompatActivity {

   Toolbar mToolbar;


    BroadcastReceiver showPokemonType = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_POKEMON_TYPE)){


                //Remplace FRAGMENT
                Fragment pokemonType = PokemonDetail.getInstance();
                //int position = intent.getIntExtra("position", -1);
                String num = intent.getStringExtra("num");
                Bundle bundle = new Bundle();
                bundle.putString("num", num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Set Pokemon Name for Toolbar
                Pokemon pokemon = Common.findPokemonByNum(num);
                mToolbar.setTitle(pokemon.getName());

            }
        }
    };

   BroadcastReceiver showDetail = new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
                if(intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Enable back button on toolBar
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true); //too

                    //Remplace FRAGMENT
                    Fragment detailFragment = PokemonDetail.getInstance();
                    //int position = intent.getIntExtra("position", -1);
                    String num = intent.getStringExtra("num");
                    Bundle bundle = new Bundle();
                    bundle.putString("num", num);
                    detailFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment);
                    fragmentTransaction.addToBackStack("detail");
                    fragmentTransaction.commit();

                    //Set Pokemon Name for Toolbar
                    Pokemon pokemon = Common.findPokemonByNum(num);
                    mToolbar.setTitle(pokemon.getName());

                }
       }
   };

    BroadcastReceiver showEvolution = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_NUM_EVOLUTION)){

                //Remplace FRAGMENT
                Fragment detailFragment = PokemonDetail.getInstance();

                Bundle bundle = new Bundle();
                String num = intent.getStringExtra("num");
                bundle.putString("num", num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(detailFragment);   //Remove current fragment
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Set Pokemon Name for Toolbar
                Pokemon pokemon = Common.findPokemonByNum(num);
                mToolbar.setTitle(pokemon.getName());

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("POKEMON LIST");
        setSupportActionBar(mToolbar);

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, new IntentFilter(Common.KEY_ENABLE_HOME));

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolution, new IntentFilter(Common.KEY_NUM_EVOLUTION));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                mToolbar.setTitle("POKEMON LIST");

                //Clear all fragment detail and pop to list fragment
                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            default:
                break;


        }
        return true;
    }
}
