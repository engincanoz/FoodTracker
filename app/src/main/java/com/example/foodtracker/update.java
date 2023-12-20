package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class update extends AppCompatActivity {
    ProductRepository productRepository;
    EditText allergensText, unwantedsText;
    Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        productRepository = new ProductRepository(this);
        Pair<ArrayList<String>, ArrayList<String>> userAllergensAndUnwanteds = productRepository.retrieveUserAllergensAndUnwanteds();
        ArrayList<String> allergens = userAllergensAndUnwanteds.first;
        ArrayList<String> unwanteds = userAllergensAndUnwanteds.second;
        allergensText = findViewById(R.id.editTextTextAllergens);
        String text1 = "";
        String text2 = "";
        for(int i = 0; i < allergens.size();i++){
            text1 += allergens.get(i)+ " ";
        }
        unwantedsText = findViewById(R.id.editTextTextUnwanteds);
        for(int i = 0; i < unwanteds.size();i++){
            text2 += unwanteds.get(i)+ " ";
        }
        allergensText.setText(text1);
        unwantedsText.setText(text2);

        okButton = findViewById(R.id.button2);
        cancelButton = findViewById(R.id.cancel);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String allergens = allergensText.getText().toString();
                Toast.makeText(update.this, allergens, Toast.LENGTH_SHORT).show();
                String unwanteds = unwantedsText.getText().toString();

                productRepository.insertOrUpdateUserData(allergens, unwanteds);

                Intent intent = new Intent(update.this,dietHelp.class);

                startActivity(intent);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(update.this,recycle.class);

                startActivity(intent);

            }
        });
    }
}