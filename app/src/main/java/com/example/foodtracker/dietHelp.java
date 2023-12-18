package com.example.foodtracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;


public class    dietHelp extends AppCompatActivity {
   Context context;
    ProductController controller;
    //Product newProduct = new Product(null, null, null, null,null);
    private Button saveIngredientsButton;
    String ingredients;
    //UI views
    private MaterialButton inputImageBtn;
    private MaterialButton recognizeTextBtn;
    private ShapeableImageView imageIv;
    private EditText recognizedTextEt;
    private static final    String TAG = "MAIN_TAG";
    private Uri imageUri = null;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    String promptText = "";
    String gender;
    String lifeStyle;
    String dietPlan;
    String kgText ="";

    BottomNavigationView bottomNavigationView;
    Button button;
    int kgValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_help);
        context = this;

        Button generateButton = findViewById(R.id.generateButton);
        generateButton.setEnabled(false);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.dietHelp_icon);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int selectedItemId = item.getItemId();

                // Check if the selected item is already the current item
                if (selectedItemId != bottomNavigationView.getSelectedItemId()) {
                    if (selectedItemId == R.id.dietHelp_icon) {
                        return true;
                    } else if (selectedItemId == R.id.profile_icon) {
                        startActivity(new Intent(getApplicationContext(), update.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.myProducts_icon) {
                        startActivity(new Intent(getApplicationContext(), recycle.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.addProduct_icon) {
                        startActivity(new Intent(getApplicationContext(), ocrScan.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }
                }

                return false;
            }
        });




        RadioGroup radioGroup1 = findViewById(R.id.radiogroupGENDER);

        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);


        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = radioButton1.getText().toString(); // Set the selected option to a specific value
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = radioButton2.getText().toString(); // Set the selected option to a specific value
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });


        RadioGroup radioGroup = findViewById(R.id.radiogroupLIFESTYLE); // Replace with your RadioGroup's ID

        RadioButton radioButton3 = findViewById(R.id.radioButton3); // Replace with your RadioButton IDs
        RadioButton radioButton4 = findViewById(R.id.radioButton4);

        RadioButton radioButton6 = findViewById(R.id.radioButton6);


        // Create custom OnClickListener for each RadioButton
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "Current lifestyle is moderately active"; // Set the selected option to a specific value
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is lightly active"; // Set the selected option to a specific value
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });


        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is highly active"; // Set the selected option to a specific value
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });


        Button buttonDiet1 = findViewById(R.id.ProteinDiet); // Replace with your Button IDs
        Button buttonDiet2 = findViewById(R.id.KetogenicDiet);
        Button buttonDiet3 = findViewById(R.id.WeightlossDiet);
        Button buttonDiet4 = findViewById(R.id.WeightGainDiet);

        buttonDiet1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dietPlan = "My diet plan is protein diet";
                buttonDiet1.setEnabled(false);
                buttonDiet2.setEnabled(true);
                buttonDiet3.setEnabled(true);
                buttonDiet4.setEnabled(true);
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        buttonDiet2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dietPlan = "My diet plan is ketogenic diet";
                buttonDiet1.setEnabled(true);
                buttonDiet2.setEnabled(false);
                buttonDiet3.setEnabled(true);
                buttonDiet4.setEnabled(true);
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        buttonDiet3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dietPlan = "My diet plan is weight loss diet";
                buttonDiet1.setEnabled(true);
                buttonDiet2.setEnabled(true);
                buttonDiet3.setEnabled(false);
                buttonDiet4.setEnabled(true);
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        buttonDiet4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dietPlan = "My diet plan is weight gain diet";
                buttonDiet1.setEnabled(true);
                buttonDiet2.setEnabled(true);
                buttonDiet3.setEnabled(true);
                buttonDiet4.setEnabled(false);
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });


        EditText kgInput = findViewById(R.id.kgInput);

        kgInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Update the instance variable kgText instead of the local variable
                kgText = kgInput.getText().toString();

                // Check if all choices are made and the input field is not empty
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        /*button = findViewById(R.id.button8);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(dietHelp.this, dietDisplay.class);

                // If you want to pass data to the new activity, you can use intent.putExtra()
                // intent.putExtra("key", "value");

                // Start the new activity
                startActivity(intent);
            }
        });*/
    }
    private void updatePromptText() {
        promptText = String.format("Generate a 200-word daily nutrition plan for morning, noon, and evening based on gender %s, lifestyle %s, diet plan %s, and weight %s. Calculate the basic calorie needs first." +
                "Assume a serious approach as a helpful dietician.", gender, lifeStyle, dietPlan, kgText);

    }
    public void launchGenerate(View v){

        Intent i = new Intent(this, dietDisplay.class);
        //i.putExtra("PROMPT", promptText);
        startActivity(i);
    }



}








