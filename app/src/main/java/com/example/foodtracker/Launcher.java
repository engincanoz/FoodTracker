package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class Launcher extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(Launcher.this);
        if (databaseHelper.isDatabaseEmpty()) {
            Intent intent = new Intent(Launcher.this, login.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(Launcher.this, MyProducts.class);
            startActivity(intent);
        }
    }
}