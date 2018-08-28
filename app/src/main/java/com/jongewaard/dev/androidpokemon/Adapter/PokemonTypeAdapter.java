package com.jongewaard.dev.androidpokemon.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jongewaard.dev.androidpokemon.Common.Common;
import com.jongewaard.dev.androidpokemon.Interface.IItemClickListener;
import com.jongewaard.dev.androidpokemon.R;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.List;


public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder> {

    Context mContext;
    List<String> typeList;

    public PokemonTypeAdapter(Context context, List<String> typeList) {
        mContext = context;
        this.typeList = typeList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.chip_item, parent, false);
        return new MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.chip.setChipText(typeList.get(position));
        holder.chip.changeBackgroundColor(Common.getColorByType(typeList.get(position)));
        holder.setIItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return typeList.size();
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
