package com.example.foodtracker;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;*/
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ArrayList;

public class myProducts extends AppCompatActivity {
    Button button;
    ArrayList<ProductModel> models = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);
        button = findViewById(R.id.button4); // Replace with the ID of your CardView

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event here
                // For example, you can launch a new activity, show a toast, etc.
                Toast.makeText(getApplicationContext(), "Product", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(myProducts.this, productx.class);

                // If you want to pass data to the new activity, you can use intent.putExtra()
                // intent.putExtra("key", "value");

                // Start the new activity
                startActivity(intent);
            }
        });
    }
    private void setProductModels() {

        ArrayList<String> productnames = new ArrayList<>();
        ArrayList<String> productFreshness = new ArrayList<>();
        ArrayList<String> expirationDate = new ArrayList<>();

        productFreshness.add("4");productFreshness.add("5");productFreshness.add("6");
        productnames.add("p1");productnames.add("p2");productnames.add("p3");
        expirationDate.add("date1");expirationDate.add("date2");expirationDate.add("date3");





        for (int i = 0;i<productnames.size();i++) {
            models.add(new ProductModel(productnames.get(i),productFreshness.get(i),expirationDate.get(i)));
        }
    }


    BottomNavigationView bottomNavigationView;

/*    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);*/

      /*  bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.my_products);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override*/
    /*  public boolean onNavigationItemSelected(@NonNull MenuItem item) {*/
          /*      int selectedItemId = item.getItemId();

                // Check if the selected item is already the current item
                if (selectedItemId != bottomNavigationView.getSelectedItemId()) {
                    if (selectedItemId == R.id.diet_help) {
                        startActivity(new Intent(getApplicationContext(), dietHelp.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.profile) {
                        startActivity(new Intent(getApplicationContext(), login.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.my_products) {
                        return true;
                    } else if (selectedItemId == R.id.add_Product) {
                    startActivity(new Intent(getApplicationContext(), addProduct.class));
                    overridePendingTransition(0, 0);
                    }
                }
                return false;
            }
        });*/












           /* setContentView(R.layout.activity_recyclerview);
            RecyclerView recView = findViewById(R.id.recyclerView);
            setProductModels();
            Recyclerviewadapter adapter = new Recyclerviewadapter(this,models);
            recView.setAdapter(adapter);
            recView.setLayoutManager(new LinearLayoutManager(this));
            // Add space between items
            int verticalSpaceHeight = 30; // Adjust this value as needed
            recView.addItemDecoration(new SpacesItemDecoration(verticalSpaceHeight));*/

}

