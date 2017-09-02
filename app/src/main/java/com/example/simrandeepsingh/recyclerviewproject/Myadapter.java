package com.example.simrandeepsingh.recyclerviewproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simrandeepsingh on 18/08/17.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private List<Listitem> listitems;
    private Context context;

    public Myadapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Listitem listitem=listitems.get(position);
        holder.tver.setText(listitem.getTvver());
        holder.tname.setText(listitem.getTvname());
        holder.tlevel.setText(listitem.getTvlevel());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tver,tname,tlevel;


        public ViewHolder(View itemView) {
            super(itemView);
            tver=itemView.findViewById(R.id.tv_version);
            tname =itemView.findViewById(R.id.tv_name);
            tlevel=itemView.findViewById(R.id.tv_api_level);
        }
    }
}