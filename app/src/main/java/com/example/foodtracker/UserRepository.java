package com.example.foodtracker;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public void createDatabase() {

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            // Create User table
            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS User (" +
                    "  User_ID INTEGER," +
                    "  Name TEXT NOT NULL," +
                    "  Surname TEXT NOT NULL," +
                    "  Age INTEGER NOT NULL," +
                    "  Weight NUMERIC NOT NULL," +
                    "  Height NUMERIC NOT NULL," +
                    "  Gender TEXT NOT NULL," +
                    "  Daily_Activity_Level TEXT NOT NULL," +
                    "  Allergens TEXT," +
                    "  Unwanted_Ingredients TEXT" +
                    ")";
            try (PreparedStatement userTableStatement = connection.prepareStatement(createUserTableQuery)) {
                userTableStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User insertOrUpdateUserData(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            String insertOrUpdateDataQuery = "INSERT OR REPLACE INTO User " +
                    "(User_ID, Name, Surname, Age, Weight, Height, Gender, Daily_Activity_Level, " +
                    "Allergens, Unwanted_Ingredients) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertOrUpdateDataQuery)) {

                statement.setInt(1, 1);
                statement.setString(2, user.getName());
                statement.setString(3, user.getSurName());

                statement.setString(9, user.getAllergens());
                statement.setString(10, user.getUnwantedIngredients());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User retrieveUserInfo() {
        User user = null;
        String retrieveUserDataQuery = "SELECT * FROM User LIMIT 1";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(retrieveUserDataQuery)) {

            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("Name"),
                        resultSet.getString("Surname"),
                        resultSet.getString("Allergens"),
                        resultSet.getString("Unwanted_Ingredients"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}