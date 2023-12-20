package com.example.foodtracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
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
    ArrayList<String> name, freshness;
    ArrayList<Integer> id;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.recycler);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.myProducts_icon);
        MenuItem selectedItem = bottomNavigationView.getMenu().findItem(R.id.myProducts_icon);
        selectedItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
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
        productRepository = new ProductRepository(recycle.this);
        id = new ArrayList<>();
        name = new ArrayList<>();

        freshness = new ArrayList<>();
        storeData();

        recyclerviewadapter = new Recyclerviewadapter(recycle.this, this, name, id, freshness);
        recyclerView.setAdapter(recyclerviewadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recycle.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1) {
            recreate();
        }
    }

    void storeData() {
        Cursor cursor = productRepository.retrieveProductInfo();

            while( cursor.moveToNext()) {
                id.add(cursor.getInt(0));
                name.add(cursor.getString(1));
                freshness.add(cursor.getString(2));
        }
    }

}