package com.example.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.widget.Toast;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "foodTracker.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void insertOrUpdateProductData(Product product, Context context) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ProductID", product.productID);
        cv.put("Name", product.getName());
        cv.put("Freshness", product.freshness);

        String expirationDate = product.getExpirationDate();
        cv.put("ExpirationDate", expirationDate);

        String ingredientsString = String.join(", ", product.getIngredients());
        cv.put("Ingredients", ingredientsString);

        long result = db.insert("Product", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, recycle.class);
            context.startActivity(intent);
        }
    }

    public boolean isDatabaseEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM User", null);

        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }

        db.close();
        return count == 0;
    }


    public void deleteProductById(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Product", "ProductID=?", new String[]{String.valueOf(productId)});
        db.close();

        if (result != -1) {
            Toast.makeText(context, "Successfully deleted product: " + productId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete product: " + productId, Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor retrieveProductInfo() {
        String query = "SELECT * FROM " + "Product";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Pair<ArrayList<String>, ArrayList<String>> retrieveUserAllergensAndUnwanteds() {
        ArrayList<String> allergensList = new ArrayList<>();
        ArrayList<String> unwantedsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null) {
            String query = "SELECT Allergens, Unwanteds FROM User";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                String allergens = cursor.getString(0);
                String unwanteds = cursor.getString(1);

                allergensList = ocrScan.getList(allergens);
                unwantedsList = ocrScan.getList(unwanteds);
            }

            cursor.close();
            db.close();
        }

        return new Pair<>(allergensList, unwantedsList);
    }

    public void insertOrUpdateUserData(String allergens, String unwanteds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Allergens", allergens);
        cv.put("Unwanteds", unwanteds);


        int rowsAffected = db.update("User", cv, null, null);

        if (rowsAffected == 0) {

            long result = db.insert("User", null, cv);

            if (result == -1) {
                Toast.makeText(context, "User Data Insert Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "New User Data Added successfully", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "User Data Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }
    public void onCreate(SQLiteDatabase db) {

        String createProductTableQuery = "CREATE TABLE Product(" +
                " ProductID INT PRIMARY KEY," +
                " Name TEXT," +
                " Freshness TEXT," +
                " ExpirationDate TEXT," +
                " PurchaseDate TEXT," +
                " Ingredients TEXT" +
                ")";

        db.execSQL(createProductTableQuery);

        String createUserTableQuery = "CREATE TABLE User(" +
                " Allergens TEXT," +
                " Unwanteds TEXT" +
                ")";
        db.execSQL(createUserTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "User");
        db.execSQL("DROP TABLE IF EXISTS " + "Products");
        onCreate(db);
    }

    public Cursor getProductData(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "ProductID",
                "Name",
                "Freshness",
                "ExpirationDate",
                "PurchaseDate",
                "Ingredients"
        };

        String selection = "ProductID = ?";
        String[] selectionArgs = {String.valueOf(productId)};

        Cursor cursor = db.query(
                "Product",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor;
    }

    public ArrayList<String> retrieveUserList() {
        ArrayList<String> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null) {
            String query = "SELECT * FROM User";
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                // Assuming "Allergens" and "Unwanteds" are columns in the "User" table
                String allergens = cursor.getString(0);
                String unwanteds = cursor.getString(1);
                ArrayList<String> allergensList = ocrScan.getList(allergens);
                ArrayList<String> unwantedList = ocrScan.getList(unwanteds);

                for (int i = 0; i < allergensList.size(); i++) {
                    userList.add(allergensList.get(i));
                }
                for (int i = 0; i < unwantedList.size(); i++) {
                    userList.add(unwantedList.get(i));
                }

            }

            cursor.close();
            db.close();
        }

        return userList;
    }

}