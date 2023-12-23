package com.example.foodtracker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class myProducts extends AppCompatActivity {
    Button button;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Product", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(myProducts.this, productx.class);

                startActivity(intent);
            }
        });
    }

}

