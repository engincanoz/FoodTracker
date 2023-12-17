package com.example.foodtracker;
import android.Manifest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodtracker.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class    ocrScan extends AppCompatActivity {

    //UI views
    private MaterialButton inputImageBtn;
    private MaterialButton recognizeTextBtn;
    private MaterialButton saveIngredientsButton;
    private ShapeableImageView imageIv;
    private EditText recognizedTextEt;
    private static final    String TAG = "MAIN_TAG";
    private Uri imageUri = null;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private String[] cameraPermissions;
    private String[] storagePermissions;
    private ProgressDialog progressDialog;
    private TextRecognizer textRecognizer;
    public String ingredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_scan);

        inputImageBtn = findViewById(R.id.inputImageBtn);
        recognizeTextBtn = findViewById(R.id.recognizeTextBtn);
        imageIv = findViewById(R.id.imageIv);
        recognizedTextEt = findViewById(R.id.recognizedTextEt);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        saveIngredientsButton = findViewById(R.id.saveIngredients);

        saveIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextPage(v);
            }
        });


        inputImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputImageDialog();
            }
        });
        recognizeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri == null){
                    Toast.makeText(ocrScan.this, "Pick Image First", Toast.LENGTH_SHORT).show();
                }else{
                    recognizeTextFromImage();
                }

            }

        });

    }
    public static ArrayList<String> getList(String ingredients) {
        // Split the ingredients string using commas as the delimiter
        String[] ingredientsArray = ingredients.split(", ");

        // Convert the array to an ArrayList
        ArrayList<String> ingredientsList = new ArrayList<>(Arrays.asList(ingredientsArray));

        // Remove leading and trailing whitespaces from each ingredient
        for (int i = 0; i < ingredientsList.size(); i++) {
            ingredientsList.set(i, ingredientsList.get(i).trim());
        }

        return ingredientsList;
    }
    public void launchNextPage(View v) {
        Intent intent = new Intent(this, addProduct.class);
        ingredients = recognizedTextEt.getText().toString();
        ArrayList<String> ingredientsList = getList(ingredients);
        intent.putExtra("IngredientsList", ingredientsList);
        startActivity(intent);
        Toast.makeText(this, "Ingredients are: " + ingredientsList, Toast.LENGTH_SHORT).show();
    }
    private void recognizeTextFromImage() {
        Log.d(TAG, "recognizeTextFromImage: ");
        progressDialog.setMessage("Loading image...");
        progressDialog.show();

        try {
            InputImage inputImage = InputImage.fromFilePath(this, imageUri);
            progressDialog.setMessage("Scanning image...");

            Task<Text> textTaskResult = textRecognizer.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                        @Override
                        public void onSuccess(Text text) {
                            progressDialog.dismiss();
                            String recognizedText = text.getText();
                            Log.d(TAG, "onSuccess: recognizedText: " + recognizedText);
                            recognizedTextEt.setText(recognizedText);
                            ingredients = recognizedText.toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Log.d(TAG, "onFailure: ",e);
                            Toast.makeText(ocrScan.this, "Failed to scanning text due to "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
            Log.d(TAG, "recognizeTextFromImage: ",e);
            Toast.makeText(this, "Failed preparing image due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void showInputImageDialog() {
        PopupMenu popupMenu = new PopupMenu(this, inputImageBtn);
        popupMenu.getMenu().add(Menu.NONE,1,1, "CAMERA");
        popupMenu.getMenu().add(Menu.NONE, 2,2,"GALLERY");

        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                int id = menuItem.getItemId();
                if(id==1){
                    Log.d(TAG, "onMenuItemClick: Camera Clicked..");
                    if(checkCameraPermission()){
                        pickImageCamera();
                    }
                    else{
                        requestCameraPermissions();
                    }
                }
                else if(id == 2){
                    Log.d(TAG, "onMenuItemClick: Gallery Clicked");
                    if(checkStoragePermission()){
                        pickImageGallery();
                    }
                    else{
                        requestStoragePermission();
                    }
                }
                return true;
            }
        });
    }

    private void pickImageGallery(){
        Log.d(TAG, "pickImageGallery: ");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imageUri = data.getData();
                        Log.d(TAG, "onActivityResult: imageUri "+ imageUri);
                        imageIv.setImageURI(imageUri);
                    }
                    else{
                        Log.d(TAG, "onActivityResult: cancelled");
                        Toast.makeText(ocrScan.this, "Cancelled", Toast.LENGTH_SHORT).show();

                    }

                }
            }
    );

    private void pickImageCamera(){
        Log.d(TAG, "pickImageCamera: ");
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Sample Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Sample Description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Log.d(TAG, "onActivityResult: imageUri"+imageUri);
                        imageIv.setImageURI(imageUri);
                    }
                    else{
                        Log.d(TAG, "onActivityResult: cancelled");
                        Toast.makeText(ocrScan.this,"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){
        boolean cameraResult = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean storageResult = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return cameraResult && storageResult;
    }


    private void requestCameraPermissions(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        switch(requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickImageCamera();
                    }
                    else{
                        Toast.makeText(this, "Camera & Storage permissions are required", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length> 0){

                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(storageAccepted){
                        pickImageGallery();
                    }
                    else{
                        Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            break;
        }
    }


}