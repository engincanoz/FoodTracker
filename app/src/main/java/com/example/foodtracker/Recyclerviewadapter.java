package com.example.foodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.MyViewHolder> {
    Context context;
    /* ArrayList<ProductModel> list;*/


    ArrayList<String> name, freshness;
    ArrayList<Integer> id;
    public Recyclerviewadapter(Context context, ArrayList<String> name, ArrayList<Integer> id, ArrayList<String> freshness ) {

        this.context = context;
        this.name = name;
        this.id = id;
        this.freshness = freshness;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameText.setText(String.valueOf(name.get(position)));
        holder.idText.setText(String.valueOf(id.get(position)));
        holder.freshmessText.setText(String.valueOf(freshness.get(position)));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView freshmessText;
        TextView nameText;

        TextView idText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            freshmessText = itemView.findViewById(R.id.tvFreshness);
            nameText = itemView.findViewById(R.id.tvProductName);
            idText = itemView.findViewById(R.id.ID);
        }
    }
}


