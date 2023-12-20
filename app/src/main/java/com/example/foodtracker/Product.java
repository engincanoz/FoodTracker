package com.example.foodtracker;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;
import java.util.ArrayList;

public class Product {//implements Parcelable {
    private String name;
    private String expirationDate;

    public String freshness;



    private ArrayList<String> ingredients;
    public static int productID = 0;
    private static final int userID = 1;

    public Product(String name, String expirationDate, String freshness,
                   ArrayList<String> ingredients) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.ingredients = ingredients;
        this.freshness= freshness;
        productID++;
    }


    public String getExpirationDate() {
        return expirationDate;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }


    public static int getProductID() {
        return productID;
    }


    public static int getUserid() {
        return userID;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setProductID(int productID) {
        Product.productID = productID;
    }
}
