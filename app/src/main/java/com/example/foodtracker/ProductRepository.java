package com.example.foodtracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class ProductRepository extends SQLiteOpenHelper {
    private static Timer expirationTimer;

    private Context context;
    private static final long DELAY_MILLIS = 0; // Initial delay before the first execution
    private static final long PERIOD_MILLIS = 3600000; // 1 hour in milliseconds

    public ProductRepository(Context context) {
        super(context, "foodTracker", null, 1);
        this.context = context;
        initializeTimer();
    }

    private void initializeTimer() {
        expirationTimer = new Timer();
        expirationTimer.scheduleAtFixedRate(new ExpirationCheckTask(), DELAY_MILLIS, PERIOD_MILLIS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProductTableQuery = "CREATE TABLE IF NOT EXISTS Products (" +
                "Product_ID INTEGER PRIMARY KEY," +
                "User_ID INTEGER REFERENCES User(User_ID)," +
                "Name TEXT NOT NULL," +
                "Expiration_Date TEXT NOT NULL," +
                "Purchase_Date TEXT NOT NULL," +
                "Ingredients TEXT NOT NULL" +
                ")";
        db.execSQL(createProductTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXİSTS " + "Product");
        onCreate(db);
    }

    private class ExpirationCheckTask extends TimerTask {
        @Override
        public void run() {
            // Perform expiration check and update ArrayList here
            retrieveExpiredProducts();
        }
    }


    public void insertOrUpdateProductData(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String ingredientsString = "";
        for (int i = 0; i < product.getIngredients().size() - 1; i++) {
            ingredientsString += product.getIngredients().get(i) + ", ";
        }
        ingredientsString += product.getIngredients().get(product.getIngredients().size() - 1);

        cv.put("User_ID", 1);
        cv.put("Name", product.getName());
        cv.put("Expiration_Date", product.getExpirationDate().getTime());
        cv.put("Ingredients", ingredientsString);
        cv.put("Purchase_Date", product.getPurchaseDate().getTime());

        long result = db.insert("User", null, cv);
        if( result == -1) {
            Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Product> retrieveExpiredProducts() {
        ArrayList<Product> expiredList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        if (db != null) {
            String query = "SELECT products.Product_ID, products.User_ID, products.Name AS ProductName, " +
                    "products.Expiration_Date, products.Ingredients, products.Purchase_Date, " +
                    "products.Photo, users.Name AS UserName, users.Surname, "+
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
    }

private Product mapCursorToProduct(Cursor cursor) {


    int productId = cursor.getInt(0); // Assuming Product_ID is the first column

    String name = cursor.getString(1); // Assuming Name is the second column


    Date expirationDate = new Date(cursor.getLong(2));

    Date purchaseDate = new Date(cursor.getLong(3));



    String ingredientsString = cursor.getString(4); // Assuming Ingredients is the fifth column
    ArrayList<String> ingredients = parseIngredients(ingredientsString);



    int productID = cursor.getInt(5); // Assuming Product_ID is the sixth column

    int userID = cursor.getInt(6); // Assuming User_ID is the seventh column

    return new Product(name, expirationDate, purchaseDate, ingredients);
}

    public ArrayList<Product> retrieveAllProducts() {
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
    }

}


    /*public ArrayList<ResultSet> retrieveShoppingListProducts() {
        ArrayList<ResultSet> shoppigtList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:your_database.db")) {
            String query = "SELECT products.Product_ID, products.User_ID, products.Name AS ProductName, " +
                    "products.Expiration_Date, products.Ingredients, products.Purchase_Date, " +
                    "products.Photo, users.Name AS UserName, users.Surname, users.Age, " +
                    "users.Weight, users.Height, users.Gender, users.Daily_Activity_Level, " +
                    "users.Allergens, users.Unwanted_Ingredients " +
                    "FROM products " +
                    "LEFT JOIN users ON products.User_ID = users.User_ID " +
                    "WHERE (julianday(products.Expiration_Date) - julianday(products.Purchase_Date)) * 100 / 80 < (julianday('now') - julianday(products.Purchase_Date))";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    shoppigtList.add(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoppigtList;
    }*/
