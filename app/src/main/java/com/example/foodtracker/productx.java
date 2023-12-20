package com.example.foodtracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
/*import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;*/

import com.example.foodtracker.databinding.ActivityProductBinding;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class productx extends AppCompatActivity {

    TextView id, name, freshness, expiration, purchase, ingredient;
    private ActivityProductBinding binding;
    ProductRepository productRepository;
    Button back;

    Button dlt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        id = findViewById(R.id.ID);
        name = findViewById(R.id.tvProductName);
        freshness = findViewById(R.id.tvFreshness);
        expiration = findViewById(R.id.expView);

        ingredient = findViewById(R.id.ingred);
        back = findViewById(R.id.back);
        dlt = findViewById(R.id.delete);
        Intent intent = getIntent();


        int productId = intent.getIntExtra("productId", -1); // -1 is the default value if not found
        String productName = intent.getStringExtra("productName");
        String productFreshness = intent.getStringExtra("productFreshness");
        String expirationText = intent.getStringExtra("expirationDate");

        String ingredientsList = intent.getStringExtra("ingredients");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(productx.this,recycle.class);

                startActivity(intent);

            }
        });
        id.setText(String.valueOf(productId));
        name.setText(productName);
        freshness.setText(productFreshness);
        expiration.setText(expirationText);
        //purchase.setText(purchaseDate);
        ingredient.setText(ingredientsList);
        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRepository = new ProductRepository(productx.this);

                productRepository.deleteProductById(productId);

                Intent intent = new Intent(productx.this,recycle.class);

                startActivity(intent);


            }
        });
    }



}