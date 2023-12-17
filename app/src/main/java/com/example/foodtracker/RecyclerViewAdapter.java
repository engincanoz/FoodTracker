package com.example.foodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> name, expiration;

    public RecyclerViewAdapter(Context context, ArrayList<String> name, ArrayList<String> expiration) {
        this.context = context;
        this.name = name;
        this.expiration = expiration;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewrow, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameText.setText(name.get(position));
        holder.expirationDateText.setText(expiration.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expirationDateText;
        TextView nameText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            /*expirationDateText = itemView.findViewById(R.id.expirationDateText); // Replace with actual ID*/
            nameText = itemView.findViewById(R.id.tvProductName); // Replace with actual ID
        }
    }
}