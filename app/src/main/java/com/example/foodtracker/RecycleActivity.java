package com.example.foodtracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodtracker.databinding.ActivityRecycleBinding;
import com.example.foodtracker.databinding.AddproductBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProductRepository productRepository;
    private ArrayList<String> name, expiration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.recycler);
        addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecycleActivity.this, AddproductBinding.class);
                startActivity(intent);
            }
        });

        productRepository = new ProductRepository(this);
        name = new ArrayList<>();
        expiration = new ArrayList<>();
        storeData();

        recyclerViewAdapter = new RecyclerViewAdapter(this, name, expiration);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void storeData() {
        Cursor cursor = productRepository.retrieveProductInfo();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                expiration.add(cursor.getString(1));
            }
        }
    }
}