package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ProductRepository productRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productRepository = new ProductRepository(MainActivity.this);
        if (productRepository.isDatabaseEmpty()) {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, recycle.class);
            startActivity(intent);
        }
    }
}