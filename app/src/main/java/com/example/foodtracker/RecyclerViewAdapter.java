package com.example.foodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {
    Context context;
   /* ArrayList<ProductModel> list;*/

<<<<<<< Updated upstream:app/src/main/java/com/example/foodtracker/RecyclerViewAdapter.java
    ArrayList name, id, freshness;
    public  Recyclerviewadapter(Context context, ArrayList name,ArrayList id, ArrayList freshness ) {
=======
    ArrayList<String> name, freshness;
    ArrayList<Integer> id;
    public RecyclerviewAdapter(Context context, ArrayList<String> name, ArrayList<Integer> id, ArrayList<String> freshness ) {
>>>>>>> Stashed changes:app/src/main/java/com/example/foodtracker/Recyclerviewadapter.java
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
<<<<<<< Updated upstream:app/src/main/java/com/example/foodtracker/RecyclerViewAdapter.java

=======
>>>>>>> Stashed changes:app/src/main/java/com/example/foodtracker/Recyclerviewadapter.java
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
<<<<<<< Updated upstream:app/src/main/java/com/example/foodtracker/RecyclerViewAdapter.java
            freshmessText = itemView.findViewById(R.id.freshness);
            nameText = itemView.findViewById(R.id.name);
            idText = itemView.findViewById(R.id.ID);

=======
            freshmessText = itemView.findViewById(R.id.freshnessText);
            nameText = itemView.findViewById(R.id.tvProductName);
            idText = itemView.findViewById(R.id.IDnum);
>>>>>>> Stashed changes:app/src/main/java/com/example/foodtracker/Recyclerviewadapter.java
        }
    }
}


