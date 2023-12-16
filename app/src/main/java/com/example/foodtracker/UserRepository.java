package com.example.finand;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "foodTracker.db";
    private static final int DATABASE_VERSION = 1;

    public UserRepository(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public void insertOrUpdateUserData(User user) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("User_ID", 1);
            cv.put("Name", user.getName());
            cv.put("Surname", user.getSurName());
            cv.put("Allergens", user.getAllergens());
            cv.put("Unwanted_Ingredients", user.getUnwantedIngredients());

            long result = db.insert("User", null, cv);
            if( result == -1) {
                Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
            }
    }

    public Cursor retrieveUserInfo() {
       String query = "SELECT * FROM " + "User";
       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = null;
       if (db != null) {
           cursor = db.rawQuery(query,null);
       }
       return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS User (" +
                "  User_ID INTEGER," +
                "  Name TEXT," +
                "  Surname TEXT," +
                "  Allergens TEXT," +
                "  Unwanted_Ingredients TEXT" +
                ")";
        db.execSQL(createUserTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXİSTS " + "User");
        onCreate(db);
    }
}