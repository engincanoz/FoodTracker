package com.example.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
/*import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;*/

import com.example.foodtracker.databinding.ActivityProductBinding;

import java.sql.Date;

public class productx extends AppCompatActivity {

    TextView id, name, freshness, expiration, purchase;
    private ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        id = findViewById(R.id.ID);
        name = findViewById(R.id.tvProductName);
        freshness = findViewById(R.id.tvFreshness);
        expiration = findViewById(R.id.expView);
        purchase = findViewById(R.id.purchView);

        Intent intent = getIntent();


        int productId = intent.getIntExtra("productId", -1); // -1 is the default value if not found
        String productName = intent.getStringExtra("productName");
        String productFreshness = intent.getStringExtra("productFreshness");
        long expirationDateMillis = intent.getLongExtra("expirationDateMillis", 0);


        java.sql.Date expirationDate = new java.sql.Date(expirationDateMillis);

        long purchaseDateMillis = intent.getLongExtra("purchaseDateMillis", 0);


        java.sql.Date purchaseDate = new java.sql.Date(purchaseDateMillis);


        String ingredients = intent.getStringExtra("ingredients");

        id.setText(String.valueOf(productId));
        name.setText(productName);
        freshness.setText(productFreshness);
        expiration.setText(expirationDate.toString()); // Adjust format as needed
        purchase.setText(purchaseDate.toString());

    }

}