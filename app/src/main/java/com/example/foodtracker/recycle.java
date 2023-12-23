package com.example.foodtracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle extends AppCompatActivity {

    RecyclerView recyclerView;
    Recyclerviewadapter recyclerviewadapter;
    DatabaseHelper databaseHelper;
    ArrayList<String> name, freshness;
    ArrayList<Integer> id;
    BottomNavigationView bottomNavigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.recycler);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.myProducts_icon);
        MenuItem selectedItem = bottomNavigationView.getMenu().findItem(R.id.myProducts_icon);
        selectedItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(MenuItem item) {
                int selectedItemId = item.getItemId();

                if (selectedItemId != bottomNavigationView.getSelectedItemId()) {
                    if (selectedItemId == R.id.dietHelp_icon) {
                        startActivity(new Intent(getApplicationContext(), dietHelp.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.profile_icon) {
                        startActivity(new Intent(getApplicationContext(), update.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.myProducts_icon) {
                        return true;
                    } else if (selectedItemId == R.id.addProduct_icon) {
                        startActivity(new Intent(getApplicationContext(), ocrScan.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }
                }

                return false;
            }
        });
        databaseHelper = new DatabaseHelper(recycle.this);
        id = new ArrayList<>();
        name = new ArrayList<>();

        freshness = new ArrayList<>();
        storeData();

        recyclerviewadapter = new Recyclerviewadapter(recycle.this, this, name, id, freshness);
        recyclerView.setAdapter(recyclerviewadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recycle.this));

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1) {
            recreate();
        }
    }

    void storeData() {
        Cursor cursor = databaseHelper.retrieveProductInfo();

            while( cursor.moveToNext()) {
                id.add(cursor.getInt(0));
                name.add(cursor.getString(1));
                freshness.add(cursor.getString(2));
        }
    }

}