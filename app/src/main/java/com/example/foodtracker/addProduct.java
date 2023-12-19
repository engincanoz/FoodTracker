package com.example.foodtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;*/

/*import com.example.foodtracker.databinding.ActivityAddProductBinding;*/

public class addProduct extends AppCompatActivity {


    EditText name, date;
    Button add;
    String tag = "SECOND_TAG";
    ProductRepository productRepository;
    String dateString;
    ArrayList<String> target = new ArrayList<>();
    String nameText;
    ArrayList<String> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        target = login.getArrayList();
        name = findViewById(R.id.nameAdd);
        date = findViewById(R.id.dateAdd);
        add = findViewById(R.id.add);
        productRepository = new ProductRepository(addProduct.this);
        Intent i = getIntent();
        ingredientsList = i.getStringArrayListExtra("IngredientsList");
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nameText = name.getText().toString();
                /*String dateText = date.getText().toString();*/
                Log.d(tag, target.toString());
                Log.d(tag, ingredientsList.toString());
                dateString = "01-12-2023";
                // Parse the date string and obtain java.sql.Date
                Date sqlDate = parseSqlDate(dateString);
                if(checkContains(target)){
                    showErrorDialog(addProduct.this, "Alert! Unwanted-Alergic ingredients found");
                }else{
                    Product product = new Product(nameText,sqlDate,null,ingredientsList);
                    productRepository.insertOrUpdateProductData(product, addProduct.this);

                }
            }
        });


    }

    public boolean checkContains(ArrayList<String> unwanted){
        for(int i = 0; i < unwanted.size();i++){
            if(ingredientsList.contains(unwanted.get(i))){
                return true;
            }
        }
        return false;
    }
    public static void showErrorDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /*public void launchAdd(TextView v){

        if(checkContains(target)){
            showErrorDialog(this, "Alert! Unwanted-Alergic ingredients found");
        }else{
            Product product = new Product()
        }
    }*/
    private Date parseSqlDate(String dateString) {
        // Define the format of your input date string
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            // Parse the string and convert it to java.util.Date
            java.util.Date utilDate = inputFormat.parse(dateString);

            // Convert java.util.Date to java.sql.Date
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null;
        }
    }

}