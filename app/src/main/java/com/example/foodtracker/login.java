package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    Button button;
    private EditText allergensditText;
    private EditText unwantedEditText;

    private String allergens;
    private String unwanteds;
    DatabaseHelper databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.button2);

        allergensditText = findViewById(R.id.editTextTextAllergens);
        unwantedEditText = findViewById(R.id.editTextTextUnwanteds);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData(){
        databaseHelper = new DatabaseHelper(login.this);
        allergens = allergensditText.getText().toString();
        unwanteds = (unwantedEditText.getText().toString());
        databaseHelper.insertOrUpdateUserData(allergens, unwanteds);
        Intent intent = new Intent(login.this,MyProducts.class);
        startActivity(intent);
    }
}
