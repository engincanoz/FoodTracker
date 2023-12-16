package com.example.finand;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerviewadapter{
    Context context;
    ArrayList<ProductModel> list;
    public  Recyclerviewadapter(Context context, ArrayList<ProductModel> list) {
        this.list = list;
        this.context = context;
    }

/*    public Recyclerviewadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
     *//*   View view = inflater.inflate(R.layout.activity_recylerviewrow,parent,false);*//*
        return new Recyclerviewadapter.MyViewHolder(view);
    }*/

/*    @Override
    public void onBindViewHolder(@NonNull Recyclerviewadapter.MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getProductName());
        holder.freshness.setText(list.get(position).getFreshness());
        holder.expiratondate.setText(list.get(position).getExpirationdate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }*/

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView freshness;
        TextView expiratondate;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
         /*   freshness = itemView.findViewById(R.id.Freshness);
            expiratondate = itemView.findViewById(R.id.textView3);
            name = itemView.findViewById(R.id.textView13);*/
        }
    }
}
