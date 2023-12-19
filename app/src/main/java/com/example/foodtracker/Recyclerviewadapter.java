package com.example.foodtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.MyViewHolder> {
    Context context;
    /* ArrayList<ProductModel> list;*/
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
                String name = "";
                String freshness = "";
                int clickedId = id.get(clickedPosition);

                Cursor cursor = productRepository.getProductData(clickedId);
                int nameIndex = cursor.getColumnIndex("Name");
                int freshnessIndex = cursor.getColumnIndex("Freshness");
                if (cursor.moveToFirst()) {

                    if (nameIndex != -1) {
                        name = cursor.getString(nameIndex);
                    }

                    if (freshnessIndex != -1) {
                        freshness = cursor.getString(freshnessIndex);
                    }
                    String ingredients = "";
                    Date expirationDate = null;
                    Date purchaseDate = null;

                    int ingredientsIndex = cursor.getColumnIndex("Ingredients");
                    int expirationDateIndex = cursor.getColumnIndex("ExpirationDate");
                    int purchaseDateIndex = cursor.getColumnIndex("PurchaseDate");

                    if (ingredientsIndex != -1) {
                        ingredients = cursor.getString(ingredientsIndex);

                    }

                    if (expirationDateIndex != -1 && purchaseDateIndex != -1) {

                        long expirationDateMillis = cursor.getLong(expirationDateIndex);
                        long purchaseDateMillis = cursor.getLong(purchaseDateIndex);


                        // Check if the expiration date is not 0 (indicating null in millis) and convert to Date
                        if (expirationDateMillis != 0) {
                            expirationDate = new Date(expirationDateMillis);
                        }

                        // Check if the purchase date is not 0 (indicating null in millis) and convert to Date
                        if (purchaseDateMillis != 0) {
                            purchaseDate = new Date(purchaseDateMillis);
                        }
                    }



                    Intent intent = new Intent(context, productx.class);

                    // Pass the product data as extras to the new activity
                    intent.putExtra("productId", clickedId);
                    intent.putExtra("productName", name);
                    intent.putExtra("productFreshness", freshness);
                    intent.putExtra("expirationDate", expirationDate);
                    intent.putExtra("purchaseDate", purchaseDate);
                    intent.putExtra("ingredients", ingredients);
                    activity.startActivityForResult(intent, 1);
                }

                cursor.close();
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


