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


public class dietHelp extends AppCompatActivity {
    Context context;
    private MaterialButton inputImageBtn;
    private MaterialButton recognizeTextBtn;
    private ShapeableImageView imageIv;
    private EditText recognizedTextEt;
    private static final String TAG = "MAIN_TAG";
    private Uri imageUri = null;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    String promptText = "";
    String gender;
    String lifeStyle;
    String dietPlan;
    String kgText ="";

    BottomNavigationView bottomNavigationView;
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
        MenuItem selectedItem = bottomNavigationView.getMenu().findItem(R.id.dietHelp_icon);
        selectedItem.setChecked(true); 

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int selectedItemId = item.getItemId();

                if (selectedItemId != bottomNavigationView.getSelectedItemId()) {
                    if (selectedItemId == R.id.dietHelp_icon) {
                        return true;
                    } else if (selectedItemId == R.id.profile_icon) {
                        startActivity(new Intent(getApplicationContext(), update.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (selectedItemId == R.id.myProducts_icon) {
                        startActivity(new Intent(getApplicationContext(), MyProducts.class));
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
                gender = radioButton1.getText().toString();
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
                gender = radioButton2.getText().toString();
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });


        RadioGroup radioGroup = findViewById(R.id.radiogroupLIFESTYLE);

        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        RadioButton radioButton4 = findViewById(R.id.radioButton4);

        RadioButton radioButton6 = findViewById(R.id.radioButton6);



        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "Current lifestyle is moderately active";
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });

        radioButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is lightly active";
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
                lifeStyle = "current lifestyle is highly active";
                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });


        Button buttonDiet1 = findViewById(R.id.ProteinDiet);
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

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }


            public void afterTextChanged(Editable editable) {
                kgText = kgInput.getText().toString();

                if (gender != null && dietPlan != null && lifeStyle != null && !kgText.isEmpty()) {
                    generateButton.setEnabled(true);
                } else {
                    generateButton.setEnabled(false);
                }
                updatePromptText();
            }
        });
    }
    private void updatePromptText() {
        promptText = String.format("Generate a small(not much detailed) daily nutrition plan for morning, noon, and evening(by saying only the food not any advice in table structure) based on gender %s, lifestyle %s, diet plan %s, and weight %s. Give advices on what the user gonna eat in a day in standard. Emphasize the gender, kilo, diet pla and lifestyle" +
                "Assume a serious approach as a helpful dietician,at the end emphasize gender, lifestyle, dietplan and kilogram in humane way. Do not mention that this is a general suggestion to keep it as short as possible. ", gender, lifeStyle, dietPlan, kgText);

    }
    public void launchGenerate(View v){
        Intent i = new Intent(this, dietDisplay.class);
        i.putExtra("PROMPT", promptText);
        startActivity(i);
    }
}








