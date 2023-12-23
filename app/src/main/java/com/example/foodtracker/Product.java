package com.example.foodtracker;

import java.util.ArrayList;

public class Product {
    private String name;
    private String expirationDate;
    public String freshness;
    private ArrayList<String> ingredients;
    public static int productID = 0;

    public Product(String name, String expirationDate, String freshness,
                   ArrayList<String> ingredients) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.ingredients = ingredients;
        this.freshness = freshness;
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

}

