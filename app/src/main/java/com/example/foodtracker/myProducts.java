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

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_my_products);
        button = findViewById(R.id.button4); // Replace with the ID of your CardView
*/
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

