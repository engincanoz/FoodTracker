package com.example.foodtracker;

import android.database.Cursor;

public class User {

    private String allergens;
    private String unwantedIngredients;

    public User(String allergens, String unwantedIngredients) {

        this.allergens = allergens;
        this.unwantedIngredients = unwantedIngredients;
    }







    public String getAllergens() {
        return allergens;
    }

    public String getUnwantedIngredients() {
        return unwantedIngredients;
    }








    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public void setUnwantedIngredients(String unwantedIngredients) {
        this.unwantedIngredients = unwantedIngredients;
    }
}
