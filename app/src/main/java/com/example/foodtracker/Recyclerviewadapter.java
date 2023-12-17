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
    ArrayList<ProductModel> list;

    ArrayList name, expiration;
    public  Recyclerviewadapter(Context context, ArrayList name,ArrayList expiration ) {
        this.context = context;
        this.name = name;
        this.expiration = expiration;

    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerviewadapter.MyViewHolder holder, int position) {
        holder.nameText.setText(String.valueOf(name.get(position)));
       /* holder.freshness.setText(list.get(position).getFreshness());*/
        holder.expiratondateText.setText(String.valueOf(expiration.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        /*TextView freshness;*/
        TextView expiratondateText;
        TextView nameText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           /* freshness = itemView.findViewById(R.id.Freshness);*/
            expiratondateText = itemView.findViewById(R.id.exp);
            nameText = itemView.findViewById(R.id.name);
        }
    }
}
