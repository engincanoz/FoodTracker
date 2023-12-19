package com.example.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProductRepository extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "foodTracker.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    private static final long DELAY_MILLIS = 0; // Initial delay before the first execution
    private static final long PERIOD_MILLIS = 3600000; // 1 hour in milliseconds

    public ProductRepository(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void insertOrUpdateProductData(Product product, Context context) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ProductID", product.productID);
        cv.put("Name", product.getName());
        cv.put("Freshness", product.freshness);

        // Parse the expiration date string and store it as milliseconds
        String expirationDate = product.getExpirationDate();
        cv.put("ExpirationDate", expirationDate);

        // Parse the purchase date string and store it as milliseconds
        String purchaseDate = product.getPurchaseDate();
        cv.put("PurchaseDate", purchaseDate);

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


    public void insertOrUpdateUserData(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Allergens", user.getAllergens());
        cv.put("Unwanteds", user.getUnwantedIngredients());

        long result = db.insert("User", null, cv);
        if (result == -1) {
            Toast.makeText(context, "User Data Insert Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "User Data Added successfully", Toast.LENGTH_SHORT).show();
        }
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


    public Cursor retrieveUserInfo() {
        String query = "SELECT * FROM " + "User";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    @Override
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



        /* private void initializeTimer() {
        expirationTimer = new Timer();
        expirationTimer.scheduleAtFixedRate(new ExpirationCheckTask(), DELAY_MILLIS, PERIOD_MILLIS);
    }*/






/*    private class ExpirationCheckTask extends TimerTask {
        @Override
        public void run() {
            // Perform expiration check and update ArrayList here
          *//*  retrieveExpiredProducts();*//*
        }
    }*/

//


    /*public ArrayList<Product> retrieveExpiredProducts() {
        ArrayList<Product> expiredList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        if (db != null) {
            String query = "SELECT products.Product_ID, products.User_ID, products.Name AS ProductName, " +
                    "products.Expiration_Date, products.Ingredients, products.Purchase_Date, " +
                    "users.Name AS UserName, users.Surname, "+
                    "users.Allergens, users.Unwanted_Ingredients " +
                    "FROM products " +
                    "LEFT JOIN users ON products.User_ID = users.User_ID " +
                    "WHERE products.Expiration_Date < date('now')";

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                Product product = mapCursorToProduct(cursor);
                expiredList.add(product);
            }

            cursor.close();
            db.close();
        }

        return expiredList;
    }*/

/*private Product mapCursorToProduct(Cursor cursor) {


    int productId = cursor.getInt(0); // Assuming Product_ID is the first column

    String name = cursor.getString(1); // Assuming Name is the second column


    Date expirationDate = new Date(cursor.getLong(2));

    Date purchaseDate = new Date(cursor.getLong(3));



    String ingredientsString = cursor.getString(4); // Assuming Ingredients is the fifth column
    ArrayList<String> ingredients = parseIngredients(ingredientsString);



    int productID = cursor.getInt(5); // Assuming Product_ID is the sixth column

    int userID = cursor.getInt(6); // Assuming User_ID is the seventh column

    return new Product(name, expirationDate, purchaseDate, ingredients);
}*/

 /*   public ArrayList<Product> retrieveAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        if (db != null) {
            String query = "SELECT Product_ID, User_ID, Name, Expiration_Date, Ingredients, " +
                    "Purchase_Date FROM products";

            cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                Product product = mapCursorToProduct(cursor);
                productList.add(product);
            }

            cursor.close();
            db.close();
        }

        return productList;
    }

    public void removeProduct(int productId) {
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            try {

                String whereClause = "Product_ID = ?";
                String[] whereArgs = {String.valueOf(productId)};

                db.delete("Products", whereClause, whereArgs);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        }
    }
    private ArrayList<String> parseIngredients(String ingredientsString) {
        ArrayList<String> ingredients = new ArrayList<>();

        if (ingredientsString != null && !ingredientsString.isEmpty()) {
            String[] ingredientArray = ingredientsString.split(",");

            ingredients.addAll(Arrays.asList(ingredientArray));
        }

        return ingredients;
    }*/
}