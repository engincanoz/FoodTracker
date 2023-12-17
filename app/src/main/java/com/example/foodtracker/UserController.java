package com.example.foodtracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

public class UserController {
    /*private ProductRepository productRepository;
    public UserController(Context context) {
        this.productRepository = new ProductRepository(context);
    }




    public void fillUnwantedIngredients(User user, String unwantedIngredients) {

        user.setUnwantedIngredients(unwantedIngredients);

        .insertOrUpdateUserData(user);
    }
    public void fillAllergens(User user, String allergens) {

        user.setUnwantedIngredients(allergens);

        .insertOrUpdateUserData(user);
    }
    public void removeUnwantedIngredient(User user, String ingredientToRemove) {

        if (user != null && user.getUnwantedIngredients() != null && !user.getUnwantedIngredients().isEmpty()) {
            String[] existingIngredients = user.getUnwantedIngredients().split(", ");

            ArrayList<String> updatedIngredientsList = new ArrayList<>(Arrays.asList(existingIngredients));

            updatedIngredientsList.remove(ingredientToRemove);

            String updatedUnwantedIngredients = String.join(", ", updatedIngredientsList);

            user.setUnwantedIngredients(updatedUnwantedIngredients);

            .insertOrUpdateUserData(user);
        }

    }
    public User fillPersonalData(String allergens, String unwantedIngredients) {

        User newUser = new User(allergens, unwantedIngredients);

        .insertOrUpdateUserData(newUser);

        return newUser;
    }
    public void removeAllergens(User user, String allergen) {

        if (user != null && user.getAllergens() != null && !user.getAllergens().isEmpty()) {
            String[] existingAllergens = user.getAllergens().split(", ");

            ArrayList<String> updatedAllergens = new ArrayList<>(Arrays.asList(existingAllergens));

            updatedAllergens.remove(allergen);

            String updatedAllergensString = String.join(", ", updatedAllergens);

            user.setAllergens(updatedAllergensString);

            productRepository.insertOrUpdateProductData(user);
        }*/

        }/*
    }*/
