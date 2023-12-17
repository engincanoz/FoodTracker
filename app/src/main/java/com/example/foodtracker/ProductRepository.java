package com.example.foodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
    public void insertOrUpdateProductData(Product product) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("Name", product.getName());
            cv.put("Expiration", product.getExpirationDate());

            long result = db.insert("Product", null, cv);
            if( result == -1) {
                Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createProductTableQuery = "CREATE TABLE Product(" +
                    " Name TEXT," +
                    " Expiration TEXT" +
                    ")";

            db.execSQL(createProductTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + "Products");
        onCreate(db);
    }




/*    private void initializeTimer() {
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