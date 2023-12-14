package com.example.foodtracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



public class dietHelp extends AppCompatActivity {

    String promptText = "";
    String gender;
    String lifeStyle;
    String dietPlan;
    String kgText ="";

    int kgValue = 0; // Initialize kgValue to a default value
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_help);





        RadioGroup radioGroup1 = findViewById(R.id.radiogroupGENDER);

        RadioButton radioButton1 = findViewById(R.id.radioButton1);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);


        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = radioButton1.getText().toString(); // Set the selected option to a specific value
                updatePromptText();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = radioButton2.getText().toString(); // Set the selected option to a specific value
                updatePromptText();
            }
        });


        RadioGroup radioGroup = findViewById(R.id.radiogroupLIFESTYLE); // Replace with your RadioGroup's ID

        RadioButton radioButton3 = findViewById(R.id.radioButton3); // Replace with your RadioButton IDs
        RadioButton radioButton4 = findViewById(R.id.radioButton4);
        RadioButton radioButton5 = findViewById(R.id.radioButton5);
        RadioButton radioButton6 = findViewById(R.id.radioButton6);
        RadioButton radioButton7 = findViewById(R.id.radioButton7);

        // Create custom OnClickListener for each RadioButton
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "Current lifestyle is sedentary"; // Set the selected option to a specific value
                updatePromptText();
            }
        });

        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is lightly active"; // Set the selected option to a specific value
                updatePromptText();
            }
        });

        radioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is moderately active"; // Set the selected option to a specific value
                updatePromptText();
            }
        });

        radioButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is highly active"; // Set the selected option to a specific value
                updatePromptText();
            }
        });

        radioButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyle = "current lifestyle is super active"; // Set the selected option to a specific value
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
                updatePromptText();
            }
        });


        EditText kgInput = findViewById(R.id.kgInput);
        Button generateButton = findViewById(R.id.button8);
        generateButton.setEnabled(false);
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
    }
    private void updatePromptText() {
        promptText = String.format("Generate a 200-word daily nutrition plan for morning, noon, and evening based on gender %s, lifestyle %s, diet plan %s, and weight %s. Calculate the basic calorie needs first." +
                "Assume a serious approach as a helpful dietician.", gender, lifeStyle, dietPlan, kgText);

    }
    public void launchGenerate(View v){

        Intent i = new Intent(this, dietDisplay.class);
        i.putExtra("PROMPT", promptText);
        startActivity(i);
    }



}








