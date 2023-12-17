package com.example.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;*/

/*import com.example.foodtracker.databinding.ActivityAddProductBinding;*/

public class addProduct extends AppCompatActivity {

  EditText name, date;
  Button add;
    ProductRepository productRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);

        name = findViewById(R.id.nameAdd);
        date = findViewById(R.id.dateAdd);
        add = findViewById(R.id.add);
        productRepository = new ProductRepository(addProduct.this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameText = name.getText().toString();
                String dateText = date.getText().toString();
                Product product = new Product(nameText, dateText);

                productRepository.insertOrUpdateProductData(product);
            }
        });

    }

}