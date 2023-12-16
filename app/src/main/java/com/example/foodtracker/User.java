package com.example.foodtracker;
import android.database.Cursor;

public class User {
    private String name;
    private String surName;
    private String allergens;
    private String unwantedIngredients;

    public User(String name, String surName, String allergens, String unwantedIngredients) {
        this.name = name;
        this.surName = surName;
        this.allergens = allergens;
        this.unwantedIngredients = unwantedIngredients;
    }



    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }



    public String getAllergens() {
        return allergens;
    }

    public String getUnwantedIngredients() {
        return unwantedIngredients;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }



    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public void setUnwantedIngredients(String unwantedIngredients) {
        this.unwantedIngredients = unwantedIngredients;
    }
}
