package com.example.foodtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.MyViewHolder> {
    Context context;
    /* ArrayList<ProductModel> list;*/

    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.onItemClickListener = listener;
    }


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
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();


                String clickedName = name.get(clickedPosition);
                int clickedId = id.get(clickedPosition);
                String clickedFreshness = freshness.get(clickedPosition);


                Intent intent = new Intent(context, dietHelp.class);


                intent.putExtra("name", clickedName);
                intent.putExtra("id", clickedId);
                intent.putExtra("freshness", clickedFreshness);


                ProductRepository productRepository = new ProductRepository(context);



             /*   intent.putExtra("additionalInfo", additionalInfo);*/


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView freshmessText;
        TextView nameText;

        TextView idText;
        LinearLayout parentLayout; // Update to LinearLayout

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout); // Update to new ID

            freshmessText = itemView.findViewById(R.id.tvFreshness);
            nameText = itemView.findViewById(R.id.tvProductName);
            idText = itemView.findViewById(R.id.ID);
        }
    }
}


