package com.example.foodtracker;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/*import com.example.foodtracker.databinding.ActivityLogin2Binding;*/

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class login extends AppCompatActivity {
    Button button;
    private EditText allergensditText;
    private EditText unwantedEditText;
   static ArrayList<String> alergensList;

   ProductRepository productRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.button2);

        allergensditText = findViewById(R.id.editTextTextAllergens);
        unwantedEditText = findViewById(R.id.editTextTextUnwanteds);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productRepository = new ProductRepository(login.this);
                String allergens = allergensditText.getText().toString();
                String unwanteds = (unwantedEditText.getText().toString());
                productRepository.insertOrUpdateUserData(allergens, unwanteds);

                Intent intent = new Intent(login.this,recycle.class);
                startActivity(intent);

}
        });
    }
}