package com.example.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class productx extends AppCompatActivity {

    TextView id, name, freshness, expiration, ingredient;
    DatabaseHelper databaseHelper;
    Button back;

    Button dlt;

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
        getIntenData(intent);



        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(productx.this, MyProducts.class);

                startActivity(intent);

            }
        });

        dlt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(productx.this);
                databaseHelper.deleteProductById(Integer.parseInt(id.getText().toString()));
                Intent intent = new Intent(productx.this,MyProducts.class);
                startActivity(intent);
            }
        });
    }

    private void getIntenData(Intent intent) {
        int productId = intent.getIntExtra("productId", -1);
        String productName = intent.getStringExtra("productName");
        String productFreshness = intent.getStringExtra("productFreshness");
        String expirationText = intent.getStringExtra("expirationDate");

        String ingredientsList = intent.getStringExtra("ingredients");
        id.setText(String.valueOf(productId));
        name.setText(productName);
        freshness.setText(productFreshness);
        expiration.setText(expirationText);
        ingredient.setText(ingredientsList);
    }
}