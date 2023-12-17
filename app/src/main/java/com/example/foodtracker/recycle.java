package com.example.foodtracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtracker.databinding.ActivityRecycleBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;

public class recycle extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    Recyclerviewadapter recyclerviewadapter;
    ProductRepository productRepository;
    ArrayList<String> name, expiration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.recycler);
        addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(recycle.this, addProduct.class);

                // If you want to pass data to the new activity, you can use intent.putExtra()
                // intent.putExtra("key", "value");

                // Start the new activity
                startActivity(intent);
            }
        });
        productRepository = new ProductRepository(recycle.this);
        name = new ArrayList<>();
        expiration = new ArrayList<>();
        storeData();

        recyclerviewadapter = new Recyclerviewadapter(recycle.this, name, expiration);
        recyclerView.setAdapter(recyclerviewadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recycle.this));

    }

    void storeData() {
        Cursor cursor = productRepository.retrieveProductInfo();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while( cursor.moveToNext()) {
                name.add(cursor.getString(0));
                expiration.add(cursor.getString(1));
            }

        }
    }

}