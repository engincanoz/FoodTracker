package com.example.finand;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

public class UserController {
    private UserRepository userRepository;
    public UserController(Context context) {
        this.userRepository = new UserRepository(context);
    }




    public void fillUnwantedIngredients(User user, String unwantedIngredients) {

        user.setUnwantedIngredients(unwantedIngredients);

        userRepository.insertOrUpdateUserData(user);
    }
    public void fillAllergens(User user, String allergens) {

        user.setUnwantedIngredients(allergens);

        userRepository.insertOrUpdateUserData(user);
    }
    public void removeUnwantedIngredient(User user, String ingredientToRemove) {

        if (user != null && user.getUnwantedIngredients() != null && !user.getUnwantedIngredients().isEmpty()) {
            String[] existingIngredients = user.getUnwantedIngredients().split(", ");

            ArrayList<String> updatedIngredientsList = new ArrayList<>(Arrays.asList(existingIngredients));

            updatedIngredientsList.remove(ingredientToRemove);

            String updatedUnwantedIngredients = String.join(", ", updatedIngredientsList);

            user.setUnwantedIngredients(updatedUnwantedIngredients);

            userRepository.insertOrUpdateUserData(user);
        }

    }
    public User fillPersonalData(String name, String surname, String allergens, String unwantedIngredients) {

        User newUser = new User(name, surname, allergens, unwantedIngredients);

        userRepository.insertOrUpdateUserData(newUser);

        return newUser;
    }
    public void removeAllergens(User user, String allergen) {

        if (user != null && user.getAllergens() != null && !user.getAllergens().isEmpty()) {
            String[] existingAllergens = user.getAllergens().split(", ");

            ArrayList<String> updatedAllergens = new ArrayList<>(Arrays.asList(existingAllergens));

            updatedAllergens.remove(allergen);

            String updatedAllergensString = String.join(", ", updatedAllergens);

            user.setAllergens(updatedAllergensString);

            userRepository.insertOrUpdateUserData(user);
        }

    }
}
