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
    DatabaseHelper databaseHelper;
    EditText allergensText, unwantedsText;
    Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseHelper = new DatabaseHelper(this);
        Pair<ArrayList<String>, ArrayList<String>> userAllergensAndUnwanteds = databaseHelper.retrieveUserAllergensAndUnwanteds();
        ArrayList<String> allergens = userAllergensAndUnwanteds.first;
        ArrayList<String> unwanteds = userAllergensAndUnwanteds.second;
        allergensText = findViewById(R.id.editTextTextAllergens);
        String text1 = getAllergensAsText(allergens);
        String text2 = getUnwantedsAsText(unwanteds);

        unwantedsText = findViewById(R.id.editTextTextUnwanteds);

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

                databaseHelper.insertOrUpdateUserData(allergens, unwanteds);

                Intent intent = new Intent(update.this,dietHelp.class);

                startActivity(intent);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(update.this,MyProducts.class);

                startActivity(intent);

            }
        });
    }

    private static String getAllergensAsText(ArrayList<String> allergens) {
        String text = "";
        for(int i = 0; i < allergens.size();i++){
            text += allergens.get(i)+ " ";
        }
        return text;
    }
    private static String getUnwantedsAsText(ArrayList<String> unwanteds) {
        String text = "";
        for(int i = 0; i < unwanteds.size();i++){
            text += unwanteds.get(i)+ " ";
        }
        return text;
    }

}