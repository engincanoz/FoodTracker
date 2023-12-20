package com.example.foodtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.MyViewHolder> {
    Context context;

    ProductRepository productRepository;
    private View.OnClickListener onItemClickListener;

    Activity activity;
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.onItemClickListener = listener;
    }



    ArrayList<String> name, freshness;
    ArrayList<Integer> id;
    public Recyclerviewadapter(Activity activity, Context context, ArrayList<String> name, ArrayList<Integer> id, ArrayList<String> freshness ) {
        this.activity = activity;
        this.context = context;
        this.name = name;
        this.id = id;
        this.freshness = freshness;
        productRepository = new ProductRepository(context);
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewrow,parent,false);
        return new MyViewHolder(view, onItemClickListener);
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
                String name = "";

                int clickedId = id.get(clickedPosition);

                Cursor cursor = productRepository.getProductData(clickedId);
                int nameIndex = cursor.getColumnIndex("Name");
                int freshnessIndex = cursor.getColumnIndex("Freshness");
                int ingredientsIndex = cursor.getColumnIndex("Ingredients");
                int expirationDateIndex = cursor.getColumnIndex("ExpirationDate");


                if (cursor.moveToFirst()) {

                    if (nameIndex != -1) {
                        name = cursor.getString(nameIndex);
                    }


                    String ingredients = "";
                    String expirationDate = cursor.getString(expirationDateIndex);





                    if (ingredientsIndex != -1) {
                        ingredients = cursor.getString(ingredientsIndex);
                    }

                    String calculatedFreshness = determineFreshness(expirationDate);
                    Intent intent = new Intent(context, productx.class);

                    // Pass the product data as extras to the new activity
                    intent.putExtra("productId", clickedId);
                    intent.putExtra("productName", name);
                    intent.putExtra("productFreshness", calculatedFreshness);
                    intent.putExtra("expirationDate", expirationDate);

                    intent.putExtra("ingredients", ingredients);
                    activity.startActivityForResult(intent, 1);
                }

                cursor.close();
            }
        });

    }
    private String determineFreshness(String expirationDate) {
        java.sql.Date expiration = parseSqlDate(expirationDate);

        if (expiration == null) {

            return "Unknown";
        }

        long oneDayMillis = 24 * 60 * 60 * 1000; // One day in milliseconds
        long fiveDaysMillis = 5 * oneDayMillis; // Five days in milliseconds

        long remaining = expiration.getTime() - System.currentTimeMillis();
        if (remaining < oneDayMillis && remaining > 0) {
            return "Expiring";
        } else if (oneDayMillis <= remaining && remaining <= fiveDaysMillis) {
            return "Good";
        } else {
            if (remaining <= 0){
                return "Expired";
            }
            return "Fresh";
        }
    }


    private java.sql.Date parseSqlDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            java.util.Date utilDate = inputFormat.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView freshmessText;
        TextView nameText;
        ImageView img;
        TextView idText;
        LinearLayout parentLayout;
        public MyViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout); // Update to new ID

            freshmessText = itemView.findViewById(R.id.tvFreshness);
            nameText = itemView.findViewById(R.id.tvProductName);
            idText = itemView.findViewById(R.id.ID);


        }
    }
}


