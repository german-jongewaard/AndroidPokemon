package com.jongewaard.dev.androidpokemon.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jongewaard.dev.androidpokemon.Common.Common;
import com.jongewaard.dev.androidpokemon.Interface.IItemClickListener;
import com.jongewaard.dev.androidpokemon.R;
import com.jongewaard.dev.androidpokemon.model.Evolution;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

public class PokemonEvolutionAdapter extends RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder> {

    Context mContext;
    List<Evolution> evolution;

    public PokemonEvolutionAdapter(Context context, List<Evolution> evolution) {
        mContext = context;
        if(evolution != null)
            this.evolution = evolution;
        else
            this.evolution = new ArrayList<>(); //Fix crash if Pokemon doesm't have Prev or next Evolution.
    }

    @NonNull
    @Override
    public PokemonEvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.chip_item, parent, false);
        return new PokemonEvolutionAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolutionAdapter.MyViewHolder holder, int position) {

        holder.chip.setChipText(evolution.get(position).getName());
        holder.chip.changeBackgroundColor(
                Common.getColorByType(


                    Common.findPokemonByNum(
                            evolution.get(position).getNum()
                    ).getType()
                        .get(0)
                )
        );

        holder.setIItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(mContext, "click to evolution Pokemon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return evolution.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Chip chip;
        IItemClickListener mIItemClickListener;

        public void setIItemClickListener(IItemClickListener IItemClickListener) {
            mIItemClickListener = IItemClickListener;
        }

        public MyViewHolder(View itemView){
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
                    mIItemClickListener.onClick(v, getAdapterPosition());
                }
            });
        }

    }
}

