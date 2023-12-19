package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class update extends AppCompatActivity {
    ProductRepository productRepository;
    EditText allergensText, unwantedsText;
    Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        allergensText = findViewById(R.id.editTextTextAllergens);
        unwantedsText = findViewById(R.id.editTextTextUnwanteds);
        okButton = findViewById(R.id.button2);
        cancelButton = findViewById(R.id.cancel);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productRepository = new ProductRepository(update.this);
                String allergens = allergensText.getText().toString();
                String unwanteds = unwantedsText.getText().toString();

                productRepository.insertOrUpdateUserData(allergens, unwanteds);

                Intent intent = new Intent(update.this,dietHelp.class);

                // If you want to pass data to the new activity, you can use intent.putExtra()
                // intent.putExtra("key", "value");

                // Start the new activity
                startActivity(intent);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(update.this,recycle.class);

                // If you want to pass data to the new activity, you can use intent.putExtra()
                // intent.putExtra("key", "value");

                // Start the new activity
                startActivity(intent);

            }
        });
    }
}